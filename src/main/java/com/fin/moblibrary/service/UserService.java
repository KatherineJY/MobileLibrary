package com.fin.moblibrary.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Random;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fin.moblibrary.domain.Account;
import com.fin.moblibrary.domain.PayRecord;
import com.fin.moblibrary.model.ResponseWrapper;
import com.fin.moblibrary.repository.AccountCrudRepository;
import com.fin.moblibrary.repository.PayRecordCrudRepository;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Service
public class UserService {
	
	@Autowired
	private AccountCrudRepository accountCrudRepository;
	
	@Autowired
	private PayRecordCrudRepository payRecordCrudRepository;
	
	private double depositMoney = 300.0;
	
	/**
	 * 登录
	 * @param phone
	 * @param pwd
	 * @return 返回account对象
	 * */
	@Transactional
	public synchronized ResponseWrapper login(String phone, String pwd) throws IOException {
		if( phone == null || phone.length() == 0)
			return new ResponseWrapper(false,"account isn't exist",null);
		Account account = accountCrudRepository.findByPhone(phone);
		if( account == null )
			return new ResponseWrapper(false, "account isn't exist", null);
		if (!decoderPwd(account.getPassword()).equals(pwd)) // 验证前对密码进行解密
			return new ResponseWrapper(false, "wrong pwd", null);
		return new ResponseWrapper(true, "", account);
	}

	
	/**
	 * 注册
	 * @param account
	 * @return 返回account对象
	 * */
	@Transactional
	public synchronized ResponseWrapper register(Account account) {
		//手机号是否为空
		if ( account.getPhone() == null || account.getPhone().length() == 0 )
			return new ResponseWrapper(false,"the phone missing",null);
		//手机号是否注册过
		if (accountCrudRepository.findByPhone(account.getPhone()) != null )
			return new ResponseWrapper(false,"the phone has registered",null);
		//用户名是否为空
		if ( account.getUsername() == null || account.getUsername().length() == 0 )
			return new ResponseWrapper(false,"the username missing",null);
		//密码是否为空
		if ( account.getPassword() == null || account.getPassword().length() == 0 )
			return new ResponseWrapper(false,"the password missing",null);
		
		account.setPassword( encoderPwd(account.getPassword()) );
		account.setBalance(0.0);
		account.setDeposit(false);
		accountCrudRepository.save(account);
		return new ResponseWrapper(true,"",account);
	}

	
	/**
	 * 缴纳/退还押金
	 * @param accoutId
	 * @param is_deposit
	 * */
	@Transactional
	public synchronized ResponseWrapper deposit(Integer accoutId, boolean is_deposit) {
		Account account = accountCrudRepository.findOne(accoutId);
		if( account == null )
			return new ResponseWrapper(false, "account isn't exist", null);
		if( account.isDeposit() == is_deposit )
			return new ResponseWrapper(false,"wrong operation",null);
		accountCrudRepository.updateDeposit(is_deposit, accoutId);
		//更新流水记录
		PayRecord payRecord;
		java.util.Date datePre = new java.util.Date();
		Timestamp timestamp = new Timestamp(datePre.getTime());
		if( account.isDeposit() ) {
			String detail = "deposit and it costs "+ depositMoney +" yuan.";
			payRecord = new PayRecord(accoutId, true, depositMoney, account.getBalance(), timestamp, detail);
		}
		else {
			String detail = "return deposit "+ depositMoney +" yuan.";
			payRecord = new PayRecord(accoutId, false, depositMoney, account.getBalance(),timestamp, detail);
		}
		payRecordCrudRepository.save(payRecord);
		return new ResponseWrapper(true,"",null);
	}
	
	/**
	 * 充值
	 * @param accoutId
	 * @param amount
	 * @return 返回余额
	 * */
	@Transactional
	public synchronized ResponseWrapper recharge(Integer accoutId, double amount) {
		if( amount < 0 )
			return new ResponseWrapper(false,"amount can't be a negative ",null);
		Account account = accountCrudRepository.findOne(accoutId);
		if( account == null )
			return new ResponseWrapper(false, "account isn't exist", null);
		if( account.isDeposit()==false )
			return new ResponseWrapper(false,"deposit first",null);
		accountCrudRepository.updateBalance(account.getBalance()+amount,accoutId);
		//更新流水记录
		PayRecord payRecord;
		java.util.Date datePre = new java.util.Date();
		Timestamp timestamp = new Timestamp(datePre.getTime());
		String detail = "recharge "+ amount +" yuan. Balance is " + account.getBalance() + " yuan.";
		payRecord = new PayRecord(accoutId, true, amount, account.getBalance(), timestamp, detail);
		payRecordCrudRepository.save(payRecord);
		return new ResponseWrapper(true,"",account.getBalance());
	}
	
	/**
	 * 重置密码
	 * @param accoutId
	 * @param newPwd
	 * */
	@Transactional
	public synchronized ResponseWrapper resetPwd(Integer accoutId, String newPwd) {
		if( accountCrudRepository.findOne(accoutId) == null )
			return new ResponseWrapper(false,"account isn't exist",null);
		accountCrudRepository.updatePwd(newPwd, accoutId);
		return new ResponseWrapper(true,"",null);
	}
	
	/**
	 * 重置手机号
	 * @param accoutId
	 * @param newPhone
	 * */
	@Transactional
	public synchronized ResponseWrapper resetPhone(Integer accoutId, String newPhone) {
		//判断电话号码是否存在
		if( accountCrudRepository.findOne(accoutId) == null )
			return new ResponseWrapper(false,"account isn't exist",null);
		accountCrudRepository.updatePhone(newPhone, accoutId);
		return new ResponseWrapper(true,"",null);
	}
	
	/**
	 * 生成并发送随机验证码
	 * @param phone
	 * @throws ServerException
	 * @throws ClientException
	 * */
	public synchronized ResponseWrapper getRandomCode(String phone) throws ServerException, ClientException {
		final String product = "Dysmsapi";
		// 产品域名,开发者无需替换
		final String domain = "dysmsapi.aliyuncs.com";

		// TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
		final String accessKeyId = "LTAIElhWQZdyrIJf";
		final String accessKeySecret = "PUzIOIwnIEZxXH8P2xlETzJfsv5rMw";

		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(phone);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName("移动图书馆");
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode("SMS_94965010");
		Integer randCode = new Random().nextInt(1000000);
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		request.setTemplateParam("{\"code\":\"" + randCode + "\"}");
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		return new ResponseWrapper(true, "", randCode);
	}
	
	//加密
	private String encoderPwd(String pwd) {
		return new BASE64Encoder().encode(pwd.getBytes());
	}

	//解密
	private String decoderPwd(String pwd) throws IOException {
		return new String(new BASE64Decoder().decodeBuffer(pwd));
	}
}

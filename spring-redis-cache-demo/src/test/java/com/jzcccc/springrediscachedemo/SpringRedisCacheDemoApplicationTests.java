package com.jzcccc.springrediscachedemo;

import com.jzcccc.springrediscachedemo.model.User;
import com.jzcccc.springrediscachedemo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringRedisCacheDemoApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
//		List<User> list = userRepository.findAll();
//		list.forEach(test -> System.out.println(test.toString()));
	}

	@Test
	void add(){
		User test = new User();
		test.setAddr("sfa");
		test.setName("name");
		userRepository.save(test);
	}

	@Test
	void getAddrById(){
		System.out.println(userRepository.findNameById(4));
	}

	@Test
	void delete(){
		userRepository.deleteById(4);
	}

}

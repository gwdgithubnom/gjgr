package org.example.command;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * File Name : essen-apps - com.essen.apps.moniter.command
 * CopyRright (c) 1949-xxxx:
 * File Number：
 * Author：gwd
 * Date：on 8/2/2017
 * Modify：gwd
 * Time ：
 * Comment：
 * Description：
 * Version：
 */
public class SampleRedisApplication implements CommandLineRunner {
   /* @Autowired*/
    private StringRedisTemplate template;

  /*  @Override*/
    public void run(String... args) throws Exception {
        ValueOperations<String, String> ops = this.template.opsForValue();
        String key = "spring.boot.redis.test";
        if (!this.template.hasKey(key)) {
            ops.set(key, "foo");
        }
        System.out.println("Found key " + key + ", value=" + ops.get(key));
    }

    public static void main(String[] args) throws Exception {
        // Close the context so it doesn't stay awake listening for redis
        SpringApplication.run(SampleRedisApplication.class, args).close();
        /*
           AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
	       ctx.register(RedisConfig.class);
	       ctx.refresh();
	       StringRedisTemplate stringRedisTemplate  = ctx.getBean(StringRedisTemplate.class);
	       // Using set to set value
	       stringRedisTemplate.opsForValue().set("R", "Ram");
	       stringRedisTemplate.opsForValue().set("S", "Shyam");
	       //Fetch values from set
	       System.out.println(stringRedisTemplate.opsForValue().get("R"));
	       System.out.println(stringRedisTemplate.opsForValue().get("S"));
	       //Using Hash Operation
	       String mohan = "Mohan";
	       stringRedisTemplate.opsForHash().put("M", String.valueOf(mohan.hashCode()),mohan);
	       System.out.println(stringRedisTemplate.opsForHash().get("M", String.valueOf(mohan.hashCode())));
         */
    }
}

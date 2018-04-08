package com.job.annotation;

import org.springframework.core.Ordered;  
import org.springframework.core.annotation.Order;


import java.lang.annotation.*;  

@Retention(RetentionPolicy.RUNTIME)
//运行时可用，它告诉编译器和JVM当前这个注释是可以在运行时通过反射机制访问的

@Target({ElementType.METHOD,ElementType.TYPE})  
//指定哪些Java元素(类、方法等)可以使用你自定义的注释

//@Inherited
//子类实现了继承了父类中的注释

@Documented 
//@Documented用来表明你自定义的注释需要在JavaDoc中可见

@Order(Ordered.HIGHEST_PRECEDENCE) 
//最高优先级  
public @interface CheckLogin {
}
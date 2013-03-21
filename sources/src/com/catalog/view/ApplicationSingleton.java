package com.catalog.view;
import com.catalog.application.Application;
import com.catalog.application.ApplicationImpl;

public class ApplicationSingleton {
	static Application application = new ApplicationImpl();
	
	public static Application getApplication(){
		return application;
	}
}


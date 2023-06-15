package com.example.DroolApplication;

import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class DroolConfig {
//	private final KieServices kieServices = KieServices.Factory.get();
//
//	private KieFileSystem getKieFileSystem() throws IOException
//	{
//		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//		kieFileSystem.write(ResourceFactory.newClassPathResource("CarEligibilityRule.drl"));
//		return kieFileSystem;
//	}
//	@Bean
//	public KieContainer getKieContainer() throws IOException
//	{
//		System.out.println("Container created...");
//		getKieRepository();
//		KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
//		kb.buildAll();
//		KieModule kieModule = kb.getKieModule();
//		KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());
//		return kContainer;
//	}
//
////	private void getKieRepository()
////	{
////		final KieRepository kieRepository = kieServices.getRepository();
////		kieRepository.addKieModule(() -> kieRepository.getDefaultReleaseId());
//////		kieRepository.addKieModule(new KieModule() {
//////			public ReleaseId getReleaseId() {
//////				return kieRepository.getDefaultReleaseId();
//////			}
//////		});
////	}
//
//	@Bean
//	public KieSession getKieSession() throws IOException
//	{
//		System.out.println("session created...");
//		return getKieContainer().newKieSession();
//	}
//}


@Configuration
public class DroolConfig {
	private final KieServices kieServices = KieServices.Factory.get();
	@Bean
	public KieContainer getKieContainer() {
		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		kieFileSystem.write(ResourceFactory.newClassPathResource("rules/CarEligibilityRule.drl"));
		KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
		kb.buildAll();
		KieModule kieModule = kb.getKieModule();
		KieContainer kieContainer=kieServices.newKieContainer(kieModule.getReleaseId());
		return kieContainer;
	}
}
//}

//	private final KieServices kieServices = KieServices.Factory.get();
//
//	private KieFileSystem getKieFileSystem() throws IOException {
//		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//		kieFileSystem.write(ResourceFactory.newClassPathResource("CarEligibilityRule.drl"));
//		return kieFileSystem;
//	}
//
//	private KieContainer createKieContainer() throws IOException {
//		System.out.println("Container created...");
//		KieRepository kieRepository = kieServices.getRepository();
//		kieRepository.addKieModule(() -> kieRepository.getDefaultReleaseId());
//
//		KieBuilder kieBuilder = kieServices.newKieBuilder(getKieFileSystem());
//		kieBuilder.buildAll();
//
//		KieModule kieModule = kieBuilder.getKieModule();
//		return kieServices.newKieContainer(kieModule.getReleaseId());
//	}
//
//	@Bean
//	public KieContainer getKieContainer() throws IOException {
//		return createKieContainer();
//	}
//
//	@Bean
//	public KieSession getKieSession() throws IOException {
//		System.out.println("Session created...");
//		return getKieContainer().newKieSession();
//	}


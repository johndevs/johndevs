
package com.github.johndevs;

import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Slf4j
public class Generator {

	private static final TemplateEngine PROCESSOR = initProcessor();

	public static void main(String[] args) {
		log.info("Updating readme...");
		try (var writer = new FileWriter("README.md")) {
			PROCESSOR.process("README", initContext(), writer);
		} catch (IOException e) {
			log.error("Failed to write README file! Error: {}", e.getMessage());
		}
		log.info("Update complete.");
	}

	private static IContext initContext() {
		var context = new Context(Locale.ENGLISH);
		context.setVariable("badges", new BadgeLinkProvider());
		context.setVariable("certs", new CertificateLinkProvider());
		return context;
	}

	private static TemplateEngine initProcessor() {
		var resolver = new ClassLoaderTemplateResolver();
		resolver.setTemplateMode(TemplateMode.TEXT);
		resolver.setSuffix(".md");
		resolver.setPrefix("templates/");
		resolver.setCheckExistence(true);
		resolver.setName("Markdown Resolver");
		resolver.setCacheable(false);
		resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());

		var engine = new TemplateEngine();
		engine.setTemplateResolver(resolver);
		return engine;
	}

}

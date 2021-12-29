
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.io.FileWriter;
import java.io.IOException;

@Slf4j
public class Generator {

	private static final TemplateEngine PROCESSOR = initProcessor();
	
	public static void main(String[] args) {
		log.info("Updating readme...");
		try (var writer = new FileWriter("README.md")) {
			PROCESSOR.process("README", new Context(), writer);
		} catch (IOException e) {
			log.error("Failed to write README file! Error: {}", e.getMessage());
		}
		log.info("Update complete.");
	}

	private static final TemplateEngine initProcessor() {
		var resolver = new ClassLoaderTemplateResolver();
		resolver.setTemplateMode(TemplateMode.TEXT);
		resolver.setSuffix(".md");
		resolver.setPrefix("templates/");

		var engine = new TemplateEngine();
		engine.setTemplateResolver(resolver);

		return engine;
	}
}

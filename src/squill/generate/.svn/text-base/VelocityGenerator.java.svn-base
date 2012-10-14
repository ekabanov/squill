package squill.generate;


import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import squill.util.FileUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class VelocityGenerator {
  public String generate(String template, Object... input) {
    return generate(template, createMap(input));
  }

  private Map<String, Object> createMap(final Object... input) {
    Map<String, Object> inputs = new HashMap<String, Object>(input.length);
    for (Object ob : input) {
      inputs.put(ob.getClass().getSimpleName(), ob);
    }
    return inputs;
  }

  public String generate(String templateFile, Map<String, ? extends Object> inputs) {
    try {
      final VelocityEngine engine = initVelocity();
      final Template template = engine.getTemplate(templateFile + ".vm");
      final StringWriter writer = new StringWriter();
      Context ctx = new VelocityContext(new HashMap<String, Object>(inputs));
      template.merge(ctx, writer);
      return writer.toString();
    } catch (Exception e) {
      throw new RuntimeException("Error rendering template " + templateFile + " with inputs " + inputs, e);
    }
  }

  private VelocityEngine initVelocity() throws Exception {
    final VelocityEngine engine = new VelocityEngine();
    // TODO velocity.properties
    Properties props = new Properties();
    props.put("resource.loader", "file,class");
    props.put("class.resource.loader.class", ClasspathResourceLoader.class.getName());
    props.put("velocimacro.library", FileUtil.path(getClass())+"Macros.vm");
    engine.init(props);
    return engine;
  }
}


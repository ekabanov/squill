package squill.mgen;


import java.util.HashMap;
import java.util.Map;

import squill.generate.VelocityGenerator;


public class VelocityTableCodeGenerator implements TableCodeGenerator {
  String modelFile;

  public VelocityTableCodeGenerator(final String modelFile) {
    this.modelFile = modelFile;
  }

  public String generateJavaTableCode(final String packageName, final MapTable mapTable) {
    final Map<String,Object> inputs=new HashMap<String, Object>();
    inputs.put("pkg",packageName);
    inputs.put("table",mapTable);
    VelocityGenerator generator=new VelocityGenerator();
    return generator.generate(modelFile,inputs);
  }
}

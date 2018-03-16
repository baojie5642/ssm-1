package com.leesf;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MybatisGenerator {
  @Test public void generator() throws Exception {
    List<String> warnings = new ArrayList<String>();
    File configFile = new File(
        "F:/01_Code/01_Idea/ssm-master/src/test/generatorConfig.xml");
    ConfigurationParser cp = new ConfigurationParser(warnings);
    Configuration config = cp.parseConfiguration(configFile);
    DefaultShellCallback callback = new DefaultShellCallback(true);
    MyBatisGenerator myBatisGenerator =
        new MyBatisGenerator(config, callback, warnings);
    myBatisGenerator.generate(null);
  }
}
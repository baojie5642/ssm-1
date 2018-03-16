package com.leesf.utils;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class ResultUtils {
  private static final Logger LOG = LoggerFactory.getLogger(ResultUtils.class);

  public static void resultSuccess(Object data, OutputStreamWriter out)
      throws IOException {
    if (data == null) {
      throw new IOException("data is null!");
    }
    JsonGenerator dumpGenerator;
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      dumpGenerator = objectMapper.getJsonFactory().createJsonGenerator(out);
    } catch (IOException e) {
      LOG.info("resultSuccess error : {}", e);
      throw e;
    }
    dumpGenerator.writeStartObject();
    dumpGenerator.writeStringField("result_code", String.valueOf(0));
    dumpGenerator.writeStringField("result_msg", "Succeed!");
    dumpGenerator.writeObjectField("result_content", data);
    dumpGenerator.writeEndObject();
    dumpGenerator.flush();

  }
}

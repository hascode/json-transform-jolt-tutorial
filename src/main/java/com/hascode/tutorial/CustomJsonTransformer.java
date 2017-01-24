package com.hascode.tutorial;

import java.util.List;

import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;

public class CustomJsonTransformer {
    public static void main(String[] args) throws Exception {
        List<Object> specs = JsonUtils.classpathToList("/spec.json");
        Chainr chainr = Chainr.fromSpec(specs);

        Object inputJSON = JsonUtils.classpathToObject("/input.json");
        Object transformedOutput = chainr.transform(inputJSON);
        System.out.println(JsonUtils.toPrettyJsonString(transformedOutput));
    }
}

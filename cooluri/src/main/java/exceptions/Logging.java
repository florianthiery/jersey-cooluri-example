package exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.jamesmurty.utils.XMLBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Logging {

    public static String getMessageXML(Exception exception, String endClass) throws ParserConfigurationException, TransformerException {
        XMLBuilder xml = XMLBuilder.create("error")
                .e("message")
                .t(exception.toString())
                .up();
        for (StackTraceElement element : exception.getStackTrace()) {
            xml.e("description")
                    .t(element.getClassName() + " / " + element.getMethodName() + "() / " + element.getLineNumber())
                    .up();
            if (element.getClassName().equals(endClass)) {
                break;
            }
        }
        return xml.asString();
    }

    public static String getMessageJSON(Exception exception, String endClass) {
        JSONObject jsonobj_error = new JSONObject();
        JSONObject jsonobj_error_data = new JSONObject();
        JSONArray jsonarray_element = new JSONArray();
        for (StackTraceElement element : exception.getStackTrace()) {
            JSONObject errMessage = new JSONObject();
            errMessage.put("class", element.getClassName());
            errMessage.put("method", element.getMethodName());
            errMessage.put("line", element.getLineNumber());
            jsonarray_element.add(errMessage);
            if (element.getClassName().equals(endClass)) {
                break;
            }
        }
        // get error code
        String code = "";
        String userMessage = "";
        if (exception.toString().contains("NullPointerException")) {
            code = "101";
            userMessage = "a value is null";
        } else if (exception.toString().contains("ValidateJSONException")) {
            code = "200";
			String[] ex = exception.toString().split(": ");
            userMessage = "validate JSON failed: " + ex[1];
        } else if (exception.toString().contains("ResourceNotAvailableException")) {
            code = "201";
			String[] ex = exception.toString().split(": ");
            userMessage = "resource is not available: " + ex[1];
        } else if (exception.toString().contains("RdfException")) {
            code = "202";
			String[] ex = exception.toString().split(": ");
            userMessage = "something with rdf is wrong: " + ex[1];
        } else if (exception.toString().contains("ConfigException")) {
            code = "203";
			String[] ex = exception.toString().split(": ");
            userMessage = "something with the config file is wrong: " + ex[1];
        } else {
            code = "102";
			String[] ex = exception.toString().split(": ");
            userMessage = "something went wrong: " + ex[1];
        }
        // output
        jsonobj_error.put("errors", jsonobj_error_data);
        jsonobj_error_data.put("internalMessage", exception.toString());
        jsonobj_error_data.put("userMessage", userMessage);
        jsonobj_error_data.put("code", code);
        jsonobj_error_data.put("developerInfo", jsonarray_element);
        return jsonobj_error.toJSONString();
    }

    public static String getMessageTEXT(Exception exception, String endClass) {
        String message = "error\n";
        message += "message: \"" + exception.toString() + "\"";
        for (StackTraceElement element : exception.getStackTrace()) {
            message += "\ndescription: \"" + element.getClassName() + " / " + element.getMethodName() + "() / " + element.getLineNumber() + "\"";
            if (element.getClassName().equals(endClass)) {
                break;
            }
        }
        return message;
    }

}

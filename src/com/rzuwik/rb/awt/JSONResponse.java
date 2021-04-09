

package com.rzuwik.rb.awt;

import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONResponse extends JSONObject {
    @Getter private final int code;
    public JSONResponse(String string, int code) throws JSONException {
        super(string);
        this.code = code;
    }
}

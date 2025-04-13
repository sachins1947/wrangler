package io.cdap.wrangler.api.parser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import io.cdap.wrangler.api.annotations.PublicEvolving;


@PublicEvolving
public class ByteSize implements Token {
    private final String raw;
    private final long bytes;

    public ByteSize(String value) {
        this.raw = value;
        this.bytes = parseBytes(value);
    }

    private long parseBytes(String value) {
        String num = value.replaceAll("[^0-9.]", "");
        String unit = value.replaceAll("[0-9.]", "").toUpperCase();
        double val = Double.parseDouble(num);
        switch (unit) {
            case "KB": return (long) (val * 1024);
            case "MB": return (long) (val * 1024 * 1024);
            case "GB": return (long) (val * 1024 * 1024 * 1024);
            case "TB": return (long) (val * 1024L * 1024 * 1024 * 1024);
            default: return (long) val; // Bytes
        }
    }

    public long getBytes() {
        return bytes;
    }

    public String getRaw() {
        return raw;
    }

    @Override
    public Object value() {
        return raw;
    }

    @Override
    public TokenType type() {
        return TokenType.BYTE_SIZE; // Assumes TokenType.BYTE_SIZE is defined
    }

    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("type", TokenType.BYTE_SIZE.name());
        json.addProperty("value", raw);
        return json;
    }
}
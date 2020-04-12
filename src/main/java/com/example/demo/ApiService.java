package com.example.demo;

import java.util.Base64;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

	public String hello() {
		return "Hello World!!!";
    }
    
    public String encode(String linklist) {
        // Decode
        byte[] actualByte = Base64.getDecoder().decode(linklist);
        String result = new String(actualByte);

        // Swap characters
        char[] arr = result.toCharArray();
        StringBuilder s = new StringBuilder();

        for (int i = 0; i <=  arr.length-1; i++) {
            if (i != arr.length -1) {
                s.append(arr[i+1]);
            }
            s.append(arr[i]);
            i += 1;
        }

        // Encode result
        result = Base64.getEncoder().encodeToString(s.toString().getBytes());

        return formatData(result);
    }

    private String formatData(String data) {
        char[] arr = data.toCharArray();
        StringBuilder s = new StringBuilder();

        for (int i = 0; i <=  arr.length-1; i++) {
            if (i != arr.length -1) {
                s.append(arr[i]);
                s.append("->");
            } else {
                s.append(arr[i]);
            }
        }

        return s.toString();
    }
}

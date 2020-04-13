package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class ApiController {

	@Autowired
	private ApiService apiService;

	@RequestMapping("/")
	public String hello() {
		return apiService.hello();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/encode", produces = "application/json", consumes = "application/json")
	public ResponseEntity<LinkList> encode(@RequestBody LinkList linklist) {
		byte[] actualByte =  Base64.getMimeDecoder().decode(linklist.getLinkList());
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

		linklist.setLinkList(formatData(result));

		return new ResponseEntity<LinkList>(linklist, HttpStatus.OK);
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

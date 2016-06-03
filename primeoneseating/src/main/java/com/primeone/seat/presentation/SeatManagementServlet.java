package com.primeone.seat.presentation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;



public class SeatManagementServlet extends HttpServlet{
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    	try{
    		
    		String seat = readFile("e:/temp/seat.txt");
    		String price = readFile("e:/temp/price.json");
    		String json = "{\"status\":\"success\", \"seatmap\":[" + seat + "], \"price\":" + price + ", \"unavailable\":[\"2-7\", \"4-25\"]}";			
    		response.setContentType("application/json");
    		response.getWriter().println(json);    		
    		
    	}catch(Exception he){
    		System.out.println(he.getMessage());
    	}
    }
    
    private String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
    
    /* public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    	try{
    		
    		String json = readJsonRequest(request);
    		String action = this.getAction(json);
    		DeviceTO device = this.getData(json, DeviceTO.class);
    		Messages messages = null;
    		
    		DeviceService deviceService = new DeviceService();
    		if (StringUtils.equals(action, "save")){
        		messages = deviceService.updateDeviceName(device);    			
    		}else if (StringUtils.equals(action, "delete")){
    			deviceService.deleteDevice(device);
    		}
    		
    	    response.setContentType("application/json"); 
    	    if (messages==null || messages.isEmpty("error"))
    	    	response.getWriter().println("{\"status\":\"success\", \"msg\":\"this is message\"}");
    	    else
    	    	response.getWriter().println("{\"status\":\"error\", \"error\":[" + MessageResources.getInstance().getMessagesJson(messages, "error") + "]}");

    	}catch(Exception he){
    		//System.out.println(he.getMessage());
    		request.getSession().setAttribute("page", "/mfa/testLoad.jsp");
    		request.getSession().setAttribute("errorMsg", MessageResources.getInstance().getMessage(new Message("error.general.unknown", he.getMessage())));
    		//response.setStatus(500);
    		//super.redirectUnknownError(request, response, he.getMessage(), "/mfa/testLoad.jsp");
    	}
    }*/
}


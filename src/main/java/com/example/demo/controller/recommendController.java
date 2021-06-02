package com.example.demo.controller;


import com.example.demo.model.Recommend;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class recommendController {

    ModelAndView mav = new ModelAndView();
    String url = null;
    String sb = "";

    @GetMapping("/recommend/{item}")
    public void recommendData(@PathVariable String item){
        url = "http://127.0.0.1:5000/recommend";
    }


    @GetMapping("/noData")
    public ResponseEntity<List<Recommend>> noUserData(){
        List<Recommend> recommends = new ArrayList<Recommend>();
        url = "http://127.0.0.1:5000/nodata";
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line = null;

            int i = 0;
            while ((line = br.readLine()) != null) {

                sb = sb + line + "\n";
                System.out.println();
            }
            sb = sb.replace("{", "");
            sb = sb.replace("}", "");
            sb = sb.replace("\"", "");
            sb = sb.replace(":", "_");
            String []tokens=sb.split(",");
            for (String token : tokens) {
                Recommend r = new Recommend();
                //System.out.println(token);
                String [] t=token.split("_");
                r.setPdNo(Integer.parseInt(t[0]));
                r.setSubcateNo(t[1]);
                r.setCateNo(t[2]);
                recommends.add(r);
            }
            br.close();
        } catch (
        IOException e) {
            e.printStackTrace();
        }


        return ResponseEntity.ok(recommends);

    }





}

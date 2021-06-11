package com.example.demo.controller;


import com.example.demo.model.Recommend;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RecommendController {

    ModelAndView mav = new ModelAndView();
    String url = null;
    String sb = "";
    List<Recommend> recommends;

    @GetMapping("/recommend")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public ResponseEntity<List<Recommend>> recommendData(@RequestBody Recommend recommend){
        url = "http://127.0.0.1:5000/rec/recommend";
        String json = "";
        recommends = new ArrayList<Recommend>();

        try {

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setDoOutput(true);
            conn.setDoInput (true);
            conn.setRequestMethod("POST");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("pdNo", recommend.getPdNo());
            jsonObject.put("subcateNo", recommend.getSubcateNo());
            jsonObject.put("categoryNo", recommend.getCateNo());
            json = jsonObject.toString();

            conn.setRequestProperty("Content-type", "application/json");

            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            os.close();

            // InputStream으로 서버로 부터 응답을 받겠다는 옵션.
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line = null;

            int i = 0;
            while ((line = br.readLine()) != null) {
                sb = sb + line + "\n";
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

            //System.out.println("" + sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(recommends);

    }

    @GetMapping("/noData")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Recommend>> noUserData(){
        recommends = new ArrayList<Recommend>();
        url = "http://127.0.0.1:5000/rec/nodata";
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

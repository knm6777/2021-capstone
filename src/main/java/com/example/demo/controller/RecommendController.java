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
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RecommendController {

    ModelAndView mav = new ModelAndView();
    String url = null;
    String sb = "";

    @GetMapping("/recommend")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public ResponseEntity<List<Recommend>> recommendData(@RequestBody Recommend recommend){
        url = "http://127.0.0.1:5000/rec/recommend";
        String json = "";
        List<Recommend> temp = new ArrayList<Recommend>();
        List<Recommend> recommends = new ArrayList<Recommend>();
        List<Recommend> dif = new ArrayList<Recommend>();
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
                String[] t = token.split("_");

                r.setPdNo(Integer.parseInt(t[0]));
                r.setSubcateNo(t[1]);
                r.setCateNo(t[2]);
                r.setSimilarity(Double.parseDouble(t[3]));

                //recommends.add(r);
                if(r.getSubcateNo().equals(recommend.getSubcateNo()) && r.getPdNo() == recommend.getPdNo()){
                }
                else{
                    if (r.getSubcateNo().equals(recommend.getSubcateNo())) {
                        temp.add(r);
                    } else
                        dif.add(r);
                }



            }
            br.close();
            temp.sort(new SimiliralityComparator());
            dif.sort(new SimiliralityComparator());



            if(temp.size()<6 && !temp.isEmpty()){

                if(dif.size()>0){
                    int index = 0;
                    recommends.addAll(temp);
                    while (recommends.size() != 6) {
                        recommends.add(dif.get(index));
                        index++;
                    }
                }
            }
            else if(temp.size()>=6){
                for (Recommend value : temp) {
                    recommends.add(value);

                    //System.out.println(value.getPdNo() + " "+ value.getSubcateNo());
                    if(recommends.size()==6)
                        break;
                }
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }

        return ResponseEntity.ok(recommends);

    }

    @GetMapping("/noData")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Recommend>> noUserData(){
        List<Recommend> recommends = new ArrayList<Recommend>();
        url = "http://0.0.0.0:5000/rec/nodata";
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

    static class SimiliralityComparator implements Comparator<Recommend> {
        @Override
        public int compare(Recommend r1, Recommend r2){
            if(r1.getSimilarity() < r2.getSimilarity()) return 1;
            if(r1.getSimilarity() > r2.getSimilarity()) return -1;
            return 0;
        }
    }

}

package com.ma.dev;


import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGetExample {

    private static String token = "xxxxxx";
    private static String apiUrl = "http://test.com";
    private HttpURLConnection con = null;
    private static String postData = "\"{\\\"company_id\\\": \\\"0.00.00\\\",\\\"company_no\\\": \\\"000000\\\"}\"";
    public static void main(String[] args) {
        String body = null;
        try {
            HttpGetExample hce = new HttpGetExample();
            hce.setHttpURLConnection();
            body = hce.post();
           // System.out.println(body);
        } catch(IOException e) {
            System.out.printf(e.getMessage());
        } catch (NotFoundException e) {
            if (e.getResponse().getStatus() == 404) {
                System.out.printf(e.getMessage());
            } else {
                System.out.printf(e.getMessage());
            }
        } catch (WebApplicationException e) {
            System.out.printf(e.getMessage());
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
        System.out.printf(body.toString());
    }

    public void setHttpURLConnection()  throws IOException{
        URL url = new URL(apiUrl);
        con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("X-Access-Token", token);
    }


    public String get() throws IOException {
        con.setRequestMethod("GET");
        return this.read(con.getInputStream());
    }

    private String read(InputStream is) throws IOException {
        BufferedReader in = null;
        String inputLine;
        StringBuilder body = null;
        try {
            in = new BufferedReader(new InputStreamReader(is));

            body = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                body.append(inputLine);
            }
            in.close();

            return body.toString();
        }catch(IOException e) {
            System.out.printf(e.getMessage());
        } catch (NotFoundException e) {
            if (e.getResponse().getStatus() == 404) {
                System.out.printf(e.getMessage());
            } else {
                System.out.printf(e.getMessage());
            }
        } catch (WebApplicationException e) {
            System.out.printf(e.getMessage());
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }finally {
            this.closeQuietly(in);
        }
        return body.toString();
    }

    public String post() throws IOException {
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        DataOutputStream wr = null;

        try {
            wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();
        }catch(IOException e) {
            System.out.printf(e.getMessage());
        } catch (NotFoundException e) {
            if (e.getResponse().getStatus() == 404) {
                System.out.printf(e.getMessage());
            } else {
                System.out.printf(e.getMessage());
            }
        } catch (WebApplicationException e) {
            System.out.printf(e.getMessage());
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }finally {
            this.closeQuietly(wr);
        }
        return this.read(con.getInputStream());
    }

    protected void sendData() throws IOException {
        DataOutputStream wr = null;
        try {
            wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData);
            wr.flush();
            wr.close();
        } catch(IOException exception) {
            throw exception;
        } finally {
            this.closeQuietly(wr);
        }
    }


    protected void closeQuietly(Closeable closeable) {
        try {
            if( closeable != null ) {
                closeable.close();
            }
        } catch(IOException ex) {

        }
    }
}
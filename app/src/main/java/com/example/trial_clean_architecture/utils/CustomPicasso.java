//package com.example.trial_android_eghterafi.utils;
//
//import android.content.Context;
//import android.net.Uri;
//
//import com.squareup.picasso.OkHttp3Downloader;
//import com.squareup.picasso.Picasso;
//
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.security.KeyManagementException;
//import java.security.NoSuchAlgorithmException;
//
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSession;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//
//public class CustomPicasso {
//
//    private static Picasso sPicasso;
//
//    private CustomPicasso() {
//    }
//
//    public static Picasso getImageLoader(final Context context, String loginToken) {
//        if (sPicasso == null) {
//            Picasso.Builder builder = new Picasso.Builder(context);
//            builder.downloader(new CustomOkHttpDownloader(context, loginToken));
//            sPicasso = builder.build();
//        }
//        return sPicasso;
//    }
//
//    private static class CustomOkHttpDownloader extends OkHttp3Downloader {
//
//        private String loginToken;
//        TrustManager[] trustManager = new TrustManager[] { new TrustEverythingTrustManager() };
//        SSLContext sslContext = null;
//
//        public CustomOkHttpDownloader(Context context, String loginToken) {
//            super(context);
//            this.loginToken = loginToken;
//            try {
//                sslContext = SSLContext.getInstance("SSL");
//                sslContext.init(null, trustManager,
//                        new java.security.SecureRandom());
//            } catch (NoSuchAlgorithmException e) {
//                // do nothing
//            } catch (KeyManagementException e) {
//                // do nothing
//            }
//        }
//
//        @Override
//        protected HttpURLConnection openConnection(final Uri uri)
//                throws IOException {
//            HttpURLConnection connection = super.openConnection(uri);
//
//            if (connection instanceof HttpsURLConnection) {
//                ((HttpsURLConnection) connection)
//                        .setSSLSocketFactory(sslContext.getSocketFactory());
//                ((HttpsURLConnection) connection)
//                        .setHostnameVerifier(new VerifyEverythingHostnameVerifier());
//            }
//            connection.setRequestProperty(DataHolder.HEADER_MESSAGING_TOKEN,
//                    loginToken);
//            return connection;
//        }
//
//    }
//
//    public static class TrustEverythingTrustManager implements X509TrustManager {
//        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//            return null;
//        }
//
//        public void checkClientTrusted(
//                java.security.cert.X509Certificate[] certs, String authType) {
//        }
//
//        public void checkServerTrusted(
//                java.security.cert.X509Certificate[] certs, String authType) {
//        }
//    }
//
//    public static class VerifyEverythingHostnameVerifier implements
//            HostnameVerifier {
//
//        public boolean verify(String string, SSLSession sslSession) {
//            return true;
//        }
//    }
//
//}

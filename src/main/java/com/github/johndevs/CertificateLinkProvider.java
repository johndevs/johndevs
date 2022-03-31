package com.github.johndevs;

public class CertificateLinkProvider {

    public String getUdemyLink(String caption, String certificateId) {
        return """
                <a href="https://www.udemy.com/certificate/%s" title="Udemy - %s">
                    <img src="https://udemy-certificate.s3.amazonaws.com/image/%s.jpg" style="width:20%%"  alt="%s"/>
                </a>
                """.formatted(certificateId,caption, certificateId,caption);
    }

    public String getMoocLink(String caption, String certificateId) {
        return """
                <a href="https://certificates.mooc.fi/validate/%s" title="Helsinki University - %s">
                    <img src="https://certificates.mooc.fi/validate/%s" style="width:20%%"  alt="%s"/>
                </a>
                """.formatted(certificateId,caption, certificateId,caption);
    }

    public String getStaticLink(String caption, String fileName) {
        return """
                <img src="%s" style="width:20%%"  alt="%s"/>
                """.formatted(fileName, caption);
    }


}

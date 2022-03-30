package com.github.johndevs;

public class UdemyLinkProvider {

    public String getLink(String caption, String certificateId) {
        return """
                <a href="https://www.udemy.com/certificate/%s" title="Udemy - %s">
                    <img src="https://udemy-certificate.s3.amazonaws.com/image/%s.jpg" style="width:20%%"  alt="%s"/>
                </a>
                """.formatted(certificateId,caption, certificateId,caption);
    }
}

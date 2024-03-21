package com.volgsys.api.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ServiceNotification {

    public void senSMS(String phonenumber, String name){

        try {
            HttpResponse<String> response = Unirest.post("URL SEND GRID")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Cookie", "COOKIE SEND GRID")
                    .field("username", "")//apagado securtiy
                    .field("password", "")//apagado security
                    .field("to", "55"+phonenumber)
                        .field("from", "VolgSys")
                    .field("text", "Bem vindo " + name + ". Seu cadastro é importante para nós.")
                    .field("type", "0")
                    .asString();

            if (response.isSuccess()) {
                System.out.println("SMS enviado com sucesso!");
                System.out.println("Resposta: " + response.getBody());
            } else {
                System.out.println("Erro ao enviar SMS. Código de resposta: " + response.getStatus());
                System.out.println("Mensagem de erro: " + response.getParsingError().get().getOriginalBody());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Certifique-se de fechar o Unirest para evitar vazamentos de recursos
            Unirest.shutDown();
        }
    }

    public String sendEmail(String email, String assunto, String body) {
        Email from = new Email("ticleyton@gmail.com");
        String subject = assunto;
        Email to = new Email(email);
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid("adicionar via API sendgrid");

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            return response.getBody();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

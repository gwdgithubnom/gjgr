import javax.activation.{DataHandler, FileDataSource}
import javax.mail.internet.{MimeBodyPart, MimeMultipart}

import org.apache.commons.mail.{EmailException, HtmlEmail, MultiPartEmail}

/**  <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-email</artifactId>
            <version>1.4</version>
        </dependency>
        <dependency>
            <groupId>javax.mail</groupId>
            <artifactId>mail</artifactId>
            <version>1.4</version>
        </dependency>
  * Created by wangyake on 18/12/27.
  */
object MailCommand {
  def sendTextMail(title: String, textBody: String, to: Array[String], cc: Array[String]): Boolean = {
    val email = new HtmlEmail
    try {
      email.setHostName(System.getProperty("mail.host", "mail.srv"))
      email.setAuthentication("", "")
      email.setFrom("gwd163nom@163.com")
      for (t <- to) {
        email.addTo(t)
      }
      for (c <- cc) {
        email.addCc(c)
      }
      email.setSubject(title)
      email.setTextMsg(textBody)
      email.send
    } catch {
      case e: EmailException =>
        return false
    }
    true
  }

  def sendHtmlMail(title: String, htmlBody: String, to: Array[String], cc: Array[String]): Boolean = {
    val email = new HtmlEmail
    try {
      email.setHostName(System.getProperty("mail.host", "mail.srv"))
      email.setAuthentication("", "")
      email.setFrom("gwd163nom@163.com")
      for (t <- to) {
        email.addTo(t)
      }
      for (c <- cc) {
        email.addCc(c)
      }
      email.setContent(htmlBody, "text/html; charset=UTF-8")
      email.setSubject(title)
      email.send
    } catch {
      case e: EmailException =>
        return false
    }
    true
  }

  def sendHtmlMailWithAttachment(title: String, htmlBody: String, attachFiles: String,
                        to: Array[String], cc: Array[String]): Boolean = {
    val email = new MultiPartEmail
    try {
      email.setHostName(System.getProperty("mail.host", "mail.srv"))
      email.setAuthentication("", "")
      email.setFrom("gwd163nom@163.com")
      for (t <- to) {
        email.addTo(t)
      }
      for (c <- cc) {
        email.addCc(c)
      }

      val multipart = new MimeMultipart
      val messageBodyPart = new MimeBodyPart

      messageBodyPart.setContent(htmlBody, "text/html; charset=UTF-8")
      multipart.addBodyPart(messageBodyPart)

      for (attachFile <- attachFiles.split(",")) {
        val attachmentBodyPart = new MimeBodyPart
        val source = new FileDataSource(attachFile)
        attachmentBodyPart.setDataHandler(new DataHandler(source))
        attachmentBodyPart.setFileName(attachFile.split("/").last)
        multipart.addBodyPart(attachmentBodyPart)
      }

      email.setContent(multipart)
      email.setSubject(title)

      email.send
    } catch {
      case e: EmailException =>
        return false
    }
    true
  }
}

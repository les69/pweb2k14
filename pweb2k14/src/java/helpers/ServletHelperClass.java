/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.io.OutputStreamWriter;
import model.DbHelper;
import model.Group;
import model.User;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author les
 */
public class ServletHelperClass {

    private ServletHelperClass() {
    }

    public static String getUsernameFromCookies(Cookie[] cookies) {
        if (cookies == null) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                return cookie.getValue();
            }
        }
        return null;
    }
    /**
     * Gets username of the current user from Session or Cookies.From cookies is a deprecated version
     *
     * @param request 
     * @param useCookies 
     * @return the username of the user logged or null if there's none.
     */
    public static String getUsername(HttpServletRequest request, boolean useCookies) {
        
        if(request == null)
            return null;
        if(useCookies)
            return getUsernameFromCookies(request.getCookies());
        else
            return getUsernameFromSession(request.getSession());
            
    }
        /**
     * Retrieve username from sessione
     *
     * @param session  
     * 
     * @return the username of the user logged or null if there's none.
     */
    public static String getUsernameFromSession(HttpSession session)
    {
        return (String)session.getAttribute("username");
    }
  


        /**
     * Main method for parsing text.Given a file with $$filename$$ or $$url$$ it returns a parsed text with html references to the elements
     *
     * @param grp
     * @param text the original text
     * @param helper tha database interface
     * 
     * @return a string with the parsed text
     * 
     */
    public static String parseText(Group grp, String text, DbHelper helper) {
        List<List<String>> listsOfMatch = getMatches(text);

        //Significa che non ci sono link
        if (listsOfMatch.isEmpty()) {
            return text;
        }
        List<String> matchedStrings = listsOfMatch.get(0);
        List<String> filesToLink = listsOfMatch.get(1);

        List<String> linkedFiles = convertMatchedStrings(filesToLink, grp, helper);
        String parsedText = replaceStringsInText(text, matchedStrings, linkedFiles);

        return parsedText;

    }
        /**
     * Finds the matches for words between $$ and $$
     *
     * @param text the original text
     * @return A list of Lists of strings. The first list contains the strings between the dollars and the second contains the matched strings in the form $$word$$ 
     * 
     */
    public static List<List<String>> getMatches(String text) {
        List<String> matchedStrings = new ArrayList<>();
        List<String> stringsToReplace = new ArrayList<>();

        List<List<String>> toRet = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\$\\$(\\S+)\\$\\$");
        try {
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                stringsToReplace.add(matcher.group());
                matchedStrings.add(matcher.group(1));
            }

            toRet.add(stringsToReplace);
            toRet.add(matchedStrings);
        } catch (Exception ex) {
            Logger.getLogger(ServletHelperClass.class.getName()).log(Level.SEVERE, 
                        "Error while matching patterns in message", ex);
        }

        return toRet;
    }
            /**
     * Check if the given string is an url
     *
     * @param url
     * @return true if matched else false
     * 
     */
    public static boolean isAnUrl(String url) {
        Pattern pattern = Pattern.compile("((mailto\\:|(news|(ht|f)tp(s?))\\://){1}\\S+)");
        boolean matched = false;
        try {
            Matcher matcher = pattern.matcher(url);
            matched = matcher.matches();
        } catch (Exception ex) {
            Logger.getLogger(ServletHelperClass.class.getName()).log(Level.SEVERE, 
                        "Error while matching link pattern in message", ex);
        }
        return matched;

    }
        /**
     * Replaces all the matched strings in the parsed version
     *
     * @param text the original text
     * @param toReplace the list of strings to replace
     * @param replacements 
     * 
     * @return a string with the parsed text
     * 
     */
    public static String replaceStringsInText(String text, List<String> toReplace, List<String> replacements) {
        if (toReplace.size() != replacements.size()) {
            throw new RuntimeException("Error: Different size between original strings and replacements");
        }

        for (int i = 0; i < toReplace.size(); i++) {
            text = text.replace(toReplace.get(i), replacements.get(i));
        }
        return text;

    }
        /**
     * Puts html in the post to link the items
     *
     * @param matches the list of matched strings
     * @param g
     * @param helper the database interface
     * 
     * @return a string with the linked text
     * 
     */
    public static List<String> convertMatchedStrings(List<String> matches, Group g, DbHelper helper) {
        List<String> parsedStrings = new ArrayList<>();
        User usr = null;
        if (matches == null) {
            return null;
        }
        for (int i = 0; i < matches.size(); i++) {
            String m = matches.get(i);
            String parsed = "";
            if ((usr = helper.isAGroupFile(g, m)) == null) 
            {
                if (isAnUrl(m)) {
                    parsed = "<a href=\"" + m + "\">" + m + "</a>";
                } else // Se è un file che non esiste e nemmeno uno URL copio il nome e basta, come se non fosse stato linkato
                {
                    parsed = m;
                }
            } else {
                String hash = encryptPassword(m + g.getName()+usr.getUsername());
                parsed = "<a href=\"../CyberController?oper=getDownload&file=" + hash + "&group=" + g.getId() + "\">" + m + "</a>";

            }
            parsedStrings.add(parsed);

        }
        return parsedStrings;
    }
        /**
     * Hash a string with SHA1 algorithm
     *
     * @param password text to hash

     * 
     * @return an hashed string
     * 
     */
    public static String encryptPassword(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            Logger.getLogger(ServletHelperClass.class.getName()).log(Level.SEVERE, 
                        "Error while encrpiting password", e);
        }
        return sha1;
    }
        /**
     * Converts an array of bytes to hexadecimal
     *
     * @param hash 

     * 
     * @return a formatted string
     * 
     */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    public static String formatDate(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");       
        return dateFormat.format(date);
    }
    
    public static final String webMasterMail = "no-reply@pweb.com";
    private static final String mailhost = "mailserver.ssnet.it ";

    public static void sendMail(String from, String to, String subject, String line) {
        try {

            System.getProperties().put("mail.host", mailhost);
            // Establish a network connection for sending mail
            URL u = new URL("mailto:" + to);      // Create a mailto: URL 
            URLConnection c = u.openConnection(); // Create a URLConnection for it
            c.setDoInput(false);                  // Specify no input from this URL
            c.setDoOutput(true);                  // Specify we'll do output
            c.connect();                          // Connect to mail host
            PrintWriter out = // Get output stream to mail host
                    new PrintWriter(new OutputStreamWriter(c.getOutputStream()));
            out.println("From: " + from);
            out.println("To: " + to);
            out.println("Subject: " + subject);
            out.println();  // blank line to end the list of headers
            out.println(line);
            // Close the stream to terminate the message 
            out.close();
            // Tell the user it was successfully sent.
        } catch (Exception e) {  // Handle any exceptions, print error message.
            System.err.println(e);
            System.err.println("Usage: java SendMail [<mailhost>]");
        }

    }
    public static User getUserFromSession(HttpServletRequest request)
    {
        return (User)request.getSession().getAttribute("user");
    }
}

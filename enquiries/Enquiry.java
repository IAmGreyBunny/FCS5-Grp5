package enquiries;

import java.time.LocalDateTime;

public class Enquiry {
    private int enquiryID;
    private String messageText;
    private String senderID;
    private String recipientID;
    private LocalDateTime createdDate;

    Enquiry prev;
    Enquiry next;

    public Enquiry(int enquiryID, String messageText, String senderID, String recipientID) {
        this.enquiryID = enquiryID;
        this.messageText = messageText;
        this.senderID = senderID;
        this.recipientID = recipientID;
        this.createdDate = LocalDateTime.now();
        this.prev = null;
        this.next = null;
    }

    public int getEnquiryID() { return enquiryID; }
    public String getMessageText() { return messageText; }
    public String getSenderID() { return senderID; }
    public String getRecipientID() { return recipientID; }
    public LocalDateTime getCreatedDate() { return createdDate; }

    public void setMessageText(String messageText) { this.messageText = messageText; }
    public void setSenderID(String senderID) { this.senderID = senderID; }
    public void setRecipientID(String recipientID) { this.recipientID = recipientID; }
}


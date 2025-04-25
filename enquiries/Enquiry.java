package enquiries;

import java.time.LocalDateTime;

/**
 * Represents an individual enquiry message in the system.
 * An enquiry is composed of a message, the sender, recipient, and timestamp.
 * It also supports linking to other related replies using the doubly linked list structure.
 */
public class Enquiry {
    private int enquiryID;
    private String messageText;
    private String senderID;
    private String recipientID;
    private LocalDateTime createdDate;

    Enquiry prev;
    Enquiry next;

    /**
     * Constructs a new Enquiry object with the provided parameters.
     * The createdDate is automatically set to the current date and time.
     * @param enquiryID    The unique ID for the enquiry.
     * @param messageText  The content of the enquiry message.
     * @param senderID     The ID of the sender of the enquiry.
     * @param recipientID  The ID of the recipient of the enquiry.
     */
    public Enquiry(int enquiryID, String messageText, String senderID, String recipientID) {
        this.enquiryID = enquiryID;
        this.messageText = messageText;
        this.senderID = senderID;
        this.recipientID = recipientID;
        this.createdDate = LocalDateTime.now();
        this.prev = null;
        this.next = null;
    }

    /**
     * Gets the unique ID of this enquiry.
     * @return The enquiry's unique ID.
     */
    public int getEnquiryID() { return enquiryID; }

    /**
     * Gets the message content of the enquiry.
     * @return The message text of the enquiry.
     */
    public String getMessageText() { return messageText; }

    /**
     * Gets the sender ID of the enquiry.
     * @return The ID of the sender.
     */
    public String getSenderID() { return senderID; }

    /**
     * Gets the recipient ID of the enquiry.
     * @return The ID of the recipient.
     */
    public String getRecipientID() { return recipientID; }

    /**
     * Gets the date and time when the enquiry was created.
     * @return The creation timestamp of the enquiry.
     */
    public LocalDateTime getCreatedDate() { return createdDate; }

    public void setMessageText(String messageText) { this.messageText = messageText; }
    public void setSenderID(String senderID) { this.senderID = senderID; }
    public void setRecipientID(String recipientID) { this.recipientID = recipientID; }
}


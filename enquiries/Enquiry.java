package enquiries;

import java.time.LocalDateTime;

/**
 * Represents an enquiry message exchanged between users.
 * <p>
 * Each enquiry is part of a doubly linked list to support threaded conversations.
 * It stores metadata such as sender, recipient, timestamp, and message content.
 * </p>
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
     * Constructs a new Enquiry with the specified details.
     *
     * @param enquiryID   the unique ID for this enquiry
     * @param messageText the message content
     * @param senderID    the ID of the sender
     * @param recipientID the ID of the recipient
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

    /** @return the enquiry ID */
    public int getEnquiryID() { return enquiryID; }
     /** @return the message content */
    public String getMessageText() { return messageText; }
    /** @return the sender's ID */
    public String getSenderID() { return senderID; }
    /** @return the recipient's ID */
    public String getRecipientID() { return recipientID; }
    /** @return the date and time the enquiry was created */
    public LocalDateTime getCreatedDate() { return createdDate; }

     /**
     * Sets the message content.
     *
     * @param messageText the new message content
     */
    public void setMessageText(String messageText) { this.messageText = messageText; }
     /**
     * Sets the sender's ID.
     *
     * @param senderID the new sender ID
     */
    public void setSenderID(String senderID) { this.senderID = senderID; }
     /**
     * Sets the recipient's ID.
     *
     * @param recipientID the new recipient ID
     */
    public void setRecipientID(String recipientID) { this.recipientID = recipientID; }
}


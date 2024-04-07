package onetomany.friendrequest;


public class FriendRequestDto {
    private int requestId;
    private int senderId;
    private int receiverId;
    private RequestStatus status;


    public FriendRequestDto(int requestId, int senderId, int receiverId, RequestStatus status) {
        this.requestId = requestId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.status = status;
    }


    // Getters and setters
    public int getRequestId() {
        return requestId;
    }


    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }


    public int getSenderId() {
        return senderId;
    }


    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }


    public int getReceiverId() {
        return receiverId;
    }


    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }


    public RequestStatus getStatus() {
        return status;
    }


    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}

# Chat Application with Salon Feature

## Description

This project extends a basic chat application to implement a "salon" feature, allowing two authenticated clients to discuss privately within the same salon. Other clients are unable to join the conversation, ensuring privacy and exclusivity. The application uses files for client authentication and message storage.

## Features

- **Private Chat Rooms**: Clients can engage in private discussions within their designated salons.
- **Client Authentication**: Clients are authenticated by the server to ensure only authorized users can access a salon.
- **Message Persistence**: Messages exchanged in the salon are saved to files, allowing for message retrieval and history.
- **Multi-threading**: Each client runs in a separate thread, allowing simultaneous messaging.

## Requirements

- Java Development Kit (JDK) 8 or higher
- Basic understanding of Java networking and file handling

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/chat-salon.git
   ```
2. Navigate to the project directory:
   ```bash
   cd chat-salon
   ```
3. Compile the Java files:
   ```bash
   javac *.java
   ```
4. Run the server:
   ```bash
   java Server
   ```
5. Run the clients in separate terminal windows:
   ```bash
   java Client
   ```

## Usage

1. Upon launching the client, users will be prompted to enter their name and choose to either create a new salon or join an existing one.
2. If creating a salon, users must provide a name and password. This information is saved for future authentication.
3. Users can then chat with another authenticated client in the same salon, with messages being saved to a file for record-keeping.
4. To leave the chat, simply close the client application.

## File Structure

- `salonData.txt`: Stores the names and passwords of salons.
- `salon{id}.txt`: Stores messages exchanged in the salon, where `{id}` corresponds to the salon ID.


## Acknowledgements

- Inspired by the need for private messaging in chat applications.
- Thanks to the Java community for providing excellent resources and support.

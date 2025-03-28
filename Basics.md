# Three-Way Handshake in TCP and HTTP Protocols

The three-way handshake is a process used in TCP (Transmission Control Protocol) to establish a reliable connection between a client and a server before data transfer begins.

🖧 TCP Three-Way Handshake
SYN (Synchronize) → The client sends a SYN packet to the server to initiate a connection.

SYN-ACK (Synchronize-Acknowledge) → The server responds with SYN-ACK, acknowledging the request.

ACK (Acknowledge) → The client sends an ACK, confirming the connection.

🚀 At this point, the TCP connection is established, and data transfer can begin.

```
Client → [SYN] → Server
Server → [SYN-ACK] → Client
Client → [ACK] → Server
(✅ Connection Established)
```

# HTTP (Hypertext Transfer Protocol) Request Process

HTTP runs on top of TCP, so it uses the three-way handshake before sending a request.

TCP Handshake → (SYN → SYN-ACK → ACK)

HTTP Request → The client sends an HTTP request (e.g., GET /index.html HTTP/1.1).

Server Processes Request → The web server receives the request, finds the resource, and prepares a response.

HTTP Response → The server sends the requested webpage (e.g., HTTP/1.1 200 OK + HTML content).

```
Client → [SYN] → Server
Server → [SYN-ACK] → Client
Client → [ACK] → Server
Client → [GET /index.html HTTP/1.1] → Server
Server → [HTTP/1.1 200 OK + HTML Data] → Client
```

# How a Web Request Reaches a Web Server

When you enter a URL (e.g., https://www.google.com), the following steps occur:

1️⃣ DNS Resolution

Your browser contacts a DNS server to find the IP address of www.google.com.

Example: www.google.com → 142.250.190.14.

2️⃣ TCP Handshake (SYN, SYN-ACK, ACK)

The browser and the server establish a TCP connection.

3️⃣ TLS Handshake (for HTTPS)

If using HTTPS, a TLS handshake secures the connection with encryption.

4️⃣ HTTP Request

The browser sends an HTTP GET request to fetch the webpage.

5️⃣ Server Processes the Request

The web server (e.g., Apache, Nginx) processes the request.

It may query a database or execute backend logic.

6️⃣ HTTP Response

The server sends an HTTP response (e.g., 200 OK + HTML content).

7️⃣ Rendering in Browser

The browser parses the HTML, fetches CSS/JS files, and renders the webpage.

```
1. DNS Lookup → www.google.com → 142.250.190.14
2. TCP Handshake → SYN, SYN-ACK, ACK
3. TLS Handshake (if HTTPS)
4. HTTP Request → GET /index.html
5. Server Processing (Database, API calls, etc.)
6. HTTP Response → 200 OK + HTML
7. Browser Renders Web Page
```

# Conclusion

TCP uses a three-way handshake for reliable connections.

HTTP requests rely on TCP to send and receive data.

A web request involves DNS lookup, TCP handshake, HTTP request, and response processing.
Other Protocols are For Mail SMTP (Simple mail transfer protocol) And FTP (File transfer protocol)

# Request Between a Client and a Server (TCP + HTTP)

1️⃣ Network Components
Client: Has an OS and a Socket API (e.g., socket() in Java/Python).

Server: Also has an OS and a Socket API, with a server socket that listens for incoming connections.

2️⃣ How a Connection is Established? (3-Way Handshake)
The client initiates a connection request to the server using IP:Port.

The server socket is always open (listening for requests on a known port, like port 80 for HTTP).

🔄 TCP 3-Way Handshake
Client → Server: SYN (synchronize)

Client sends a SYN packet to initiate a connection.

Server → Client: SYN + ACK (synchronize + acknowledge)

Server acknowledges the request and sends a SYN-ACK back.

Client → Server: ACK

Client acknowledges the server's response and the connection is established.

🚀 Now, a dedicated connection (socket) exists between the client and the server!

3️⃣ Server Accepts the Request
The server’s main socket (ServerSocket) is always listening.

When a request arrives, the server calls accept(), which creates a new socket for the client.

This new socket is used for communication with that specific client.

Important:

The main server socket does not close—it keeps listening for new connections.

Each client gets its own dedicated socket to communicate with the server.

4️⃣ Handling Multiple Clients (Threads / Workers)
Servers must handle multiple clients at the same time.

To do this, each client connection is assigned a worker (thread/process).

There are two main approaches:

Multi-threaded servers (Each client gets its own thread 🧵)

Event-driven servers (Async I/O) (e.g., Node.js uses event loops 🌀)

5️⃣ Connection Closure
If a connection is non-persistent (default in HTTP/1.0), the server closes the socket after the response.

If a connection is persistent (HTTP/1.1+ Keep-Alive), the socket remains open for multiple requests.

If timeout occurs, the server closes the socket.

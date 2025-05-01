# Overview of HTTP/1.0

📅 Introduced: 1996 (RFC 1945)
🔄 Connection Type: Non-Persistent (New TCP connection for each request)
⚡ Performance: Slow due to repeated TCP handshakes
📌 Features:
✅ Supports GET, POST, and HEAD requests
❌ Does not support persistent connections (each request opens a new TCP connection)
❌ No Host Header (can’t support multiple domains on the same server)

```
GET /index.html HTTP/1.0
User-Agent: Mozilla/5.0
```

🔴 Problem: Each request requires a new TCP connection, making it inefficient for multiple files like CSS, JS, and images.

# HTTP/1.1 – Major Improvements

📅 Introduced: 1997 (RFC 2068, later updated in RFC 2616)
🔄 Connection Type: Persistent by default (Keeps TCP connection open for multiple requests)
⚡ Performance: Faster due to connection reuse and better request handling

📌 Key Improvements in HTTP/1.1:
✅ Persistent Connections (Keep-Alive) – TCP connection remains open for multiple requests, reducing handshake overhead.
✅ Chunked Transfer Encoding – Allows sending dynamic data without knowing the total content size in advance.
✅ Host Header Support – Allows multiple domains to be hosted on a single IP (essential for virtual hosting).
✅ Pipelining (optional, rarely used) – Allows multiple requests to be sent before receiving responses (but browsers mostly disabled this due to issues).
✅ Better Caching Mechanisms – Cache-Control, ETag, and If-Modified-Since headers improve performance.

```
GET /index.html HTTP/1.1
Host: www.example.com
Connection: keep-alive
```

🔵 Benefit: The connection remains open, allowing multiple files (CSS, JS, images) to be downloaded without opening new TCP connections.
🔵 Also Connection release when timeout exceed and if no time out is defined then Use FIN flag to release connecction.

```mermaid
graph TD
    subgraph "Client Layer (Frontend)"
        PublicUser[Consumer/Public]
        AdminUser["Admin / Cluster Manager"]
        WeaverUser["Weaver / Field Worker"]

        PublicApp["Consumer Page <br/> Next.js Server Components <br/> (Fast SEO/Loading)"]
        AdminApp["Admin Dashboard <br/> Next.js Client Components <br/> (Shadcn UI + Recharts)"]
        WeaverPWA["Weaver Mobile PWA <br/> React + TanStack Query <br/> (Offline First)"]
    end

    subgraph "Connectivity Layer"
        Internet((Internet))
        OfflineSync["Sync Queue / Service Worker"]
    end

    subgraph "Server Layer (Backend)"
        API["Node.js / Express API"]
        Auth["RBAC Middleware <br/> (JWT: Admin vs Public)"]
        PDFService[Puppeteer PDF Generator]
    end

    subgraph "Data Layer"
        DB[("MongoDB <br/> DPP Data & Logs")]
        CloudStore["Cloud Storage <br/> Compressed Images"]
    end

    %% Relationships
    PublicUser -->|Scans QR| PublicApp
    AdminUser -->|Log in| AdminApp
    WeaverUser -->|Input Data| WeaverPWA

    PublicApp -->|GET /dpp/public| API
    AdminApp -->|REST API| API
    
    WeaverPWA -->|Online| API
    WeaverPWA -.->|Offline Mode| OfflineSync
    OfflineSync -.->|Connectivity Restored| API

    API -->|Read/Write| DB
    API -->|Upload Media| CloudStore
    API -->|Generate Report| PDFService
````
----

```mermaid

sequenceDiagram
    participant W as Weaver (User)
    participant PWA as Weaver PWA
    participant IDB as IndexedDB (Local)
    participant API as Backend API
    participant DB as MongoDB

    Note over W, PWA: Scenario: Weaver is in a remote village (Offline)

    W->>PWA: Uploads photo & enters loom data [cite: 125]
    PWA->>PWA: Check Connectivity
    
    alt is Offline
        PWA->>IDB: Save to 'SyncQueue' store
        PWA-->>W: UI: "Saved to Device. Pending Sync." [cite: 148]
    else is Online
        PWA->>API: POST /api/v1/dpp/batch/sync [cite: 158]
    end

    Note over W, PWA: User moves to area with Internet

    PWA->>PWA: Detects 'online' event
    PWA->>IDB: Read 'SyncQueue'
    IDB-->>PWA: Return pending items
    
    loop For each pending DPP
        PWA->>API: POST /api/v1/dpp/batch/sync
        API->>DB: Update Weaver Data & Timestamps [cite: 103]
        DB-->>API: Success
        API-->>PWA: 200 OK (Sync Complete)
    end
    
    PWA->>IDB: Clear 'SyncQueue'
    PWA-->>W: UI: "All data synced successfully"

````
----

```mermaid

flowchart TD
    Start((Start)) --> AdminCreate["Admin Creates Product Batch <br/> Generates Draft DPP"]
    AdminCreate --> WeaverAssign[Assign to Weaver/Co-op]
    
    subgraph "Field Work (Weaver App)"
        WeaverAssign --> WeaverLogin[Weaver Logs in]
        WeaverLogin --> Capture[Capture Process Stages]
        Capture --> UploadPhotos[Upload Dyeing/Weaving Photos]
        UploadPhotos --> Submit[Submit Data]
    end

    Submit --> Approval{"Manager Approves? <br/>"}
    
    Approval -- No --> Reject[Return to Weaver for Correction]
    Reject --> Capture
    
    Approval -- Yes --> Finalize[Finalize DPP]
    Finalize --> GenQR[Generate QR Label & PDF]
    
    subgraph "Consumption (End User)"
        GenQR --> ScanQR[User Scans QR Code]
        ScanQR --> AuthCheck{"Has Login Token? <br/>"}
        
        AuthCheck -- No (Consumer) --> PublicView[Show Public View]
        PublicView --> ShowBasic["Product Story, Material Summary, <br/> Carbon Snapshot"]
        
        AuthCheck -- Yes (Regulator) --> RestrictedView[Show Restricted View]
        RestrictedView --> ShowDetailed["Full BOM, Supplier IDs, <br/> Audit Certs"]
    end
````

```mermaid

graph TD
    User[User / Weaver] -->|Scans QR| NextJS[Next.js Application]
    
    subgraph "Frontend / Client"
        NextJS --> Admin["Admin Dashboard <br/> (React Tables, Recharts)"]
        NextJS --> Consumer["Consumer Page <br/> (Server Components, SEO)"]
        NextJS --> PWA["Weaver PWA <br/> (Offline Mode, Camera Access)"]
    end
    
    PWA -->|Offline Storage| IndexedDB[(IndexedDB / LocalStorage)]
    PWA -->|Sync| API[Backend API]

```

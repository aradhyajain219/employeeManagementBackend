# Quick Start for GitHub Deployment

## 📝 TL;DR - Just 2 steps! (No Docker Hub needed)

### 1️⃣ ✅ Git & Push (Already Done!)
Code is already pushed to: https://github.com/aradhyajain219/employeeManagementBackend

### 2️⃣ That's It! 🎉 (Automatic!)
- Workflow runs automatically on push
- Image published to GitHub Container Registry (ghcr.io)
- **NO secrets needed** - GitHub token is automatic!

---

## 🔄 Workflow Triggers

Your GitHub Actions will run on:
- ✅ Push to `main` branch
- ✅ Push to `develop` branch  
- ✅ Pull requests to `main`
- ✅ When you create a release (creates versioned image)

---

## 📦 Using Docker Image (from GitHub Container Registry)

After workflow completes, pull and run:

```bash
# Login to GitHub Container Registry (one time)
docker login ghcr.io
# Username: your_github_username
# Password: your_github_personal_access_token (or just press Enter)

# Pull image
docker pull ghcr.io/aradhyajain219/employeeManagementBackend:latest

# Run it
docker run -p 8081:8081 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://your_host:3306/ems \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=8085829423 \
  ghcr.io/aradhyajain219/employeeManagementBackend:latest
```

---

## 🧪 Test Locally First

```bash
# Build and run with MySQL locally
docker-compose up --build

# Test API
curl http://localhost:8081/api/employees/all
```

---

## 📊 Check Deployment Status

Go to your GitHub repo → **Actions** tab to see:
- ✅ Build status
- ✅ Test results
- ✅ Docker push status
- ✅ Any errors

---

## 💡 File Structure

```
ems-backend/
├── .github/
│   └── workflows/
│       └── deploy.yml          (GitHub Actions pipeline)
├── Dockerfile                  (Docker image config)
├── docker-compose.yml          (Local dev setup)
├── .dockerignore               (Files to exclude)
├── DEPLOYMENT_SETUP.md         (Detailed guide)
└── src/
    └── main/java/com/workforce/ems/
```

---

**Questions?** Check `DEPLOYMENT_SETUP.md` for detailed troubleshooting!

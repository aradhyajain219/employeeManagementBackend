# Quick Start for GitHub Deployment

## 📝 TL;DR - Just follow these 3 steps:

### 1️⃣ Initialize Git & Push
```bash
git init
git add .
git commit -m "Initial commit with GitHub Actions setup"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/ems-backend.git
git push -u origin main
```

### 2️⃣ Add Secrets to GitHub
**Go to:** GitHub Repo → Settings → Secrets and variables → Actions

Add these 2 secrets:
```
DOCKER_USERNAME = your_docker_hub_username
DOCKER_PASSWORD = your_docker_hub_access_token
```

> Get token from: https://hub.docker.com/settings/security → New Access Token

### 3️⃣ That's It! 🎉
- Push code → Workflow runs automatically
- Image published to Docker Hub as: `your_username/ems-backend:latest`

---

## 🔄 Workflow Triggers

Your GitHub Actions will run on:
- ✅ Push to `main` branch
- ✅ Push to `develop` branch  
- ✅ Pull requests to `main`
- ✅ When you create a release (creates versioned image)

---

## 📦 Using Docker Image

After workflow completes, pull and run:

```bash
docker pull your_username/ems-backend:latest
docker run -p 8081:8081 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://your_host:3306/ems \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=8085829423 \
  your_username/ems-backend:latest
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

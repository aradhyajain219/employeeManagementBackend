# GitHub Actions Deployment Setup Guide

## 📋 Files Created

1. **`.github/workflows/deploy.yml`** - Main CI/CD pipeline
2. **`Dockerfile`** - Docker image configuration
3. **`docker-compose.yml`** - Local development setup
4. **`.dockerignore`** - Files to exclude from Docker build

---

## 🚀 Setup Steps

### Step 1: Push to GitHub
```bash
git init
git add .
git commit -m "Add GitHub Actions deployment configuration"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/ems-backend.git
git push -u origin main
```

### Step 2: Add Docker Hub Secrets to GitHub
1. Go to your GitHub repository
2. Click **Settings** → **Secrets and variables** → **Actions**
3. Click **New repository secret** and add:

   - **Name:** `DOCKER_USERNAME`  
     **Value:** Your Docker Hub username
   
   - **Name:** `DOCKER_PASSWORD`  
     **Value:** Your Docker Hub access token (NOT password)

> Get Docker Hub token: https://hub.docker.com/settings/security

### Step 3: Verify Workflow
1. Go to **Actions** tab in your GitHub repo
2. You should see "Build and Deploy to Docker Hub" workflow
3. It will run automatically on:
   - Push to `main` or `develop` branch
   - Pull requests to `main`
   - When you create a release

---

## 📦 Docker Hub Publishing

After secrets are configured:

### Automatic Publishing (on push to main)
```bash
git push origin main
```
→ Automatically builds and pushes to Docker Hub as `username/ems-backend:latest`

### Release Publishing (create a version)
```bash
git tag v1.0.0
git push origin v1.0.0
```
→ Creates release and publishes as `username/ems-backend:v1.0.0`

---

## 🐳 Local Testing with Docker Compose

### Run locally before pushing:
```bash
docker-compose up --build
```

### Services will start:
- **MySQL:** `localhost:3306` (root/8085829423)
- **EMS Backend:** `localhost:8081`

### Test the API:
```bash
curl http://localhost:8081/api/employees/all
```

### Stop services:
```bash
docker-compose down
```

---

## 🔍 Pipeline What It Does

1. **Checkout** - Clones your repository
2. **Setup Java 17** - Configures Maven environment
3. **Build** - Compiles with Maven (`mvn clean package`)
4. **Test** - Runs unit tests (`mvn test`)
5. **Docker Build & Push** - Builds image and pushes to Docker Hub
6. **Metadata** - Tags with version, branch, and SHA
7. **Release Artifacts** - Uploads JAR files to GitHub Releases
8. **Security Scan** - Scans for vulnerabilities (Trivy)

---

## 📊 Docker Image Tags

Your Docker image will be published with multiple tags:

- `username/ems-backend:latest` - Latest stable version
- `username/ems-backend:main` - Latest from main branch
- `username/ems-backend:v1.0.0` - Semantic version
- `username/ems-backend:main-abc123` - Commit SHA

---

## 🚨 Troubleshooting

### Build fails with "Maven not found"
- Ensure JDK 17 is available (workflow handles this)

### Docker login fails
- Verify `DOCKER_USERNAME` and `DOCKER_PASSWORD` secrets are set
- Check Docker Hub token is valid

### MySQL connection fails
- In `docker-compose.yml`, ensure network and credentials match
- Wait for MySQL health check before testing

### Tests fail
- Run locally: `mvn test`
- Check logs in GitHub Actions

---

## 📚 Useful Commands

```bash
# Build locally
mvn clean package

# Run with Docker locally
docker-compose up --build

# Push to GitHub (triggers workflow)
git push origin main

# Create a release
git tag v1.0.0
git push origin v1.0.0

# View workflow status
# → Go to Actions tab in GitHub
```

---

## ✅ Deployment Checklist

- [ ] GitHub repository created
- [ ] Code pushed to GitHub
- [ ] Docker Hub account created
- [ ] `DOCKER_USERNAME` secret added
- [ ] `DOCKER_PASSWORD` (token) secret added
- [ ] Local test with `docker-compose up` successful
- [ ] Push to main branch
- [ ] Verify workflow runs in Actions tab
- [ ] Check Docker Hub for published image

---

## 🎯 Next: Connect React Frontend

Your backend is now at:
- **Local:** `http://localhost:8081`
- **Docker Hub:** `docker pull username/ems-backend:latest`
- **Run in any environment:**
  ```bash
  docker run -p 8081:8081 \
    -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/ems \
    -e SPRING_DATASOURCE_USERNAME=root \
    -e SPRING_DATASOURCE_PASSWORD=8085829423 \
    username/ems-backend:latest
  ```

Update your React frontend API URL to your deployed backend!


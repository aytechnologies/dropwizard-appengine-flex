# dropwizard-appengine-flex
Example Dropwizard project that can be deployed on Google AppEngine Flexible Environment
Read our blog post explaining it all here: [Deploying Dropwizard on App Engine](http://www.aytech.ca/blog/dropwizard-app-engine-flexible-env/)

## AppEngine Flex - How to

To run the app locally you can use this command:

```
./gradlew run
```
Then you can visit the `http://localhost:8080/` and you should see *hello world - dropwizard app engine flex*

Now that you can run it locally, let's try deploying it on AppEngine Flex.

### Preparation
You can see that there is an extra folder in this project. `appengine`
We are going to put all the appengine specific files in this folder. 

```
appengine
--app.yaml
--Dockerfile
```

As you can see the structure of that folder above, there are only 2 files in that folder. 
* `app.ayml` file is where we define App Engine related variables
* `Dockerfile` is our base Dockerfile which will be used on deployment. 

### app.yaml

[app.yaml Reference](https://cloud.google.com/appengine/docs/python/config/appref)

We are setting only 3 variables:

* `env: flex` means that we are using AppEngine flex, not the standard environment
* `runtime: custom` means we are using Java 8 runtime, with no Jetty provided by the runtime.
* `service: hello-world` is just giving a name to the service we are going to deploy this app under. 

### Dockerfile

First of all, we are starting from the `gcr.io/google_appengine/openjdk8` which is the base for Java 8 Runtime of the AppEngine Flex environment. Then we are adding `app.jar` which is going to be our Dropwizard app, and `config.yaml` which is the configuration file for the app. And in the last command we are telling Docker how to run our app. Run `app.jar` with arguments `server` and `config.yanml`. 

### Jar vs ShadowJar

To create a jar file with all the dependencies you can use this command:

```
./gradlew shadowJar
```

### gcloud
[gcloud](https://cloud.google.com/sdk/gcloud/) is a tool that provides a command line interface for Google Cloud Platform. We are going to use it to deploy our app on AppEngine Flexible Environment. 

### Login
In order to be able to run commands on Google Cloud Platform using `gcloud` command you need to first login. You can do that via 

```
gcloud auth login
```

### Get ready to deploy
As you saw in `Dockerfile`, you need to have `app.jar` and `config.yaml` for the `Dockerfile` to be able to deploy to a docker container. That means you need to copy those files to the `appengine` folder. In our continuous integration we do that with a script, but here you can just copy those files. 

### Deploy
You have to run this commands from the `appengine` folder.

```
gcloud config set project my-dropwizard
gcloud app deploy --version=1.0
```

### Check Deployment
If everything goes well, you should have your dropwizard deployed and can look at it in the cloud. For that you can use this command: 

```
gcloud app browse -s hello-world
```

You can look at your app logs via 

```
gcloud app logs read
```

## Issues
Please create issues and we'll reply as soon as I can. 


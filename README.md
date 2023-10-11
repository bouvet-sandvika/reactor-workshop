# SpringBootStarter

This is a starter project for creating Spring Boot microservices. In addition to standard Spring Boot dependencies, this project contains all libraries needed for creating successful integration projects including Apache Camel support. Among included libraries are libraries for:

- Common Logging
- JSON parsing
- XML parsing
- SOAP and REST message processing

## Recap of different Maven sections

``` <parent> ``` - section refers to the parent Bill Of Material POM file that contains references to mandatory and optional dependencies included in Spring Boot project with required dependency version.

``` <groupId>, <artifactId>, <version>, <name>, <description> ``` - each project specific identification and versioning information.


``` <properties> ``` - section contains version for different dependencies or plugins.

``` <dependencies> ``` - section contains a list of all libraries that can be used in the project and optionally packed with it in a fat JAR. Each library contains ``` <version> ``` number as well. If the library is included in the parent POM file than version number is not needed unless we want to override required version.
Optionally ``` <dependency> ``` can mention ``` <scope> ``` in which library is being used and wether library is ``` <optional> ``` or not.

``` <dependencyManagement> ``` - section contains list of additional parent POM files to be included in this POM file.

``` <plugins> ``` - section contains list of plugins used to facilitate build process (e.g. package all libraries with the main code in one big fat JAR file)

## How to use

### Initial project setup:

1. Clone the project

```bash
git clone https://github.com/bouvet-middleware/SpringBootStarter.git 
```

2. Update remote to your newly, empty project created in GIT

```bash
git remote set-url origin https://github.com/bouvet-middleware/MyNewMicroservice.git
```

3. Create new Development branch

```bash
git branch -M main dev
```

4. Upload branch to GIT

```bash
git push -u origin dev
```

### Developent recomandations:

**NOTE:** We foster *shared repository model* in GitHub for creating **pull** requests as it is supported in a different GIT based repositories (GitHub, GitLab, Azure DevOps). For more references check [About collaborative development models](https://docs.github.com/en/free-pro-team@latest/github/collaborating-with-issues-and-pull-requests/about-collaborative-development-models) article on GitHUb

1. Create feature branch first that indicates feature and task you are working on:

```bash
git checkout -b "feture-<feature_num>/task-<task_num>/<short_description>"
e.g.
git checkout -b "feture-1176/task-2349/pull_request_example"
```

2. Do the initial change to the code and commit the code
3. Push feature branch back to git

```bash
git push origin "feture-<feature_num>/task-<task_num>/<short_description>"
e.g.
git push origin "feture-1176/task-2349/pull_request_example"
```

4. Go to the repository page on github. And click on **Pull requests** tab
5. On the **Pull requests** tab click on **New Pull Request** button in the repo header.
6. On the **Compare changes** side in GitHub leave main (dev) branch on the left side and chose your feature branch on the right side in comparison dialog box.
7. Click on **Create pull request** button
8. Add the comment explaining purpose of your request
9. Add one or more reviewers
10. Add yourself as Assignee and all developers that you need help to contribute to your code development
11. Click on **Create pull request**
12. Invite Reviewers to clone your code, test it and comment on it and help you with development process
13. Update your code accordingly
14. Only when all test have been passed and all comments resolved, are you allowed to merge pull request to main development branch.

#### References:
- [About collaborative development models](https://docs.github.com/en/free-pro-team@latest/github/collaborating-with-issues-and-pull-requests/about-collaborative-development-models)
- [Pull Request Tutorial](https://yangsu.github.io/pull-request-tutorial/)
[About pull requests](https://docs.github.com/en/free-pro-team@latest/github/collaborating-with-issues-and-pull-requests/about-pull-requests)
- [Creating a pull request](https://docs.github.com/en/free-pro-team@latest/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request)# reactor-workshop

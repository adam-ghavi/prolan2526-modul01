# Module 1
## Reflection 1
Through this module I learned the basics of Spring and in particular, MVC architecture. It was a bit out of my comfort zone since I had been coding using Django up until this point, which uses its own architecture, namely MVT. But after getting used to how spread-out the files are, it wasn't so bad.

As far as coding standards are concerned, I feel like the features I implemented were relatively simple, and so I'm fairly sure that there aren't any glaring issues in my code. I did however add a UUID feature to the code, as I was struggling with figuring out how to reliably make sure that Product Id's don't clash for the edit/delete feature. I'm unsure whether or not that addition adheres to coding best practices or if I was supposed to make it so that the Id's are manually assignable.

# Module 2
## Reflection 1
When I integrated SonarQube into the project, I got a warning that said:
"Dependencies are not verified because the "verification-metadata.xml" file is missing. Make sure it is safe here."

Then I proceeded to find out what it was and why it was important. Turns out verification-metadata.xml is necessary to make sure that when Gradle downloads a dependency, it is actually downloading the correct thing and not malicious code (e.g., it is essential for security). Knowing this, I learned how to generate that file using "./gradlew --write-verification-metadata sha256 build".

## Reflection 2
The current workflow verifies that the code behaves as expected via unit and functional tests, then checks for vulnerabilities and codesmells using SonarQube, then proceeds to build and deploy using Render. All of this happens automatically and is triggered with every merge to the main branch. By definition, CI/CD has been implemented, with the tests and code/security review being part of CI and the automated Render deployment constituting CD. Tests and code review ensure that new code is up to standard and doesn't break. Automated deployment ensures that the latest codebase, which has been vetted and verified, is automatically accessible to users.

# Module 4
## Reflection 1
I feel like the test driven development helped in terms of creating an outline of what features to implement and how they're supposed to behave, and making sure that behavior is maintained in every iteration of the codebase. Instinctively, however, I think that this type of web development project is naturally suited to this paradigm and thus it requires careful consideration for other fields of programming that might not be as "testable", such as game development.

## Reflection 2
F (fast): I feel like the tests were simple enough to run as to not bog down the build/integration process. I (independent): tests do not rely on eachother and can be freely swapped, so I feel like I have implemented this. R (repeatable): tests contain no random generation, so by definition they are deterministic and therefore repeatable. T (timely) I made sure to write the tests before the implementations.

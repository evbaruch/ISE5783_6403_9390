# Mini Project in Introduction to Software Engineering

**Presented by:**  
Evyatar Baruch  
Yehuda Pailent 



**Year:** 5783 – 2023

**Mentor/Lecturer:** Aliezer Ginzburg

![Project Photo](images/diamond.png)

## Table of Contents
1. [Executive Summary](#executive-summary)
2. [Introduction](#introduction)
3. [Project Scope and Objectives](#project-scope-and-objectives)
4. [Methodology](#methodology)
5. [Requirements Analysis](#requirements-analysis)
6. [System Design](#system-design)
7. [Implementation](#implementation)
8. [Testing and Quality Assurance](#testing-and-quality-assurance)  
9. [performance acceleration](#performance-acceleration)
10. [Conclusion](#conclusion)
11. [References](#references)
12. [Appendices](#appendices)

## Executive Summary

The objective of our mini-project was to gain hands-on experience in software engineering methodologies by creating a renderer in Java. The renderer serves as a software component responsible for generating visual output based on input data. Through this project, we aimed to apply the concepts and techniques learned in our Introduction to Software Engineering course while developing a practical software solution.

### Key Features:

- **Renderer Development**: We implemented a renderer from scratch using Java programming language, following best practices and design principles.
- **Input Processing**: The renderer takes input data, such as geometric models or 3D scenes, and processes it to generate visual output.
- **Rendering Techniques**: We explored various rendering techniques, such as ray tracing, to achieve realistic or stylized visual representations.
- **Performance Optimization**: We focused on optimizing the renderer's performance by utilizing efficient algorithms and data structures.
- **User Interaction**: We provided a user-friendly interface that allows users to interact with the renderer, adjust settings, and view rendered output.

### Outcomes:

- **Understanding of Software Engineering Methodologies**: By undertaking this mini-project, we gained practical experience in applying software engineering methodologies, including requirements analysis, system design, implementation, testing, and maintenance.
- **Proficiency in Java**: Through the development of the renderer in Java, we enhanced our programming skills and familiarity with object-oriented principles.
- **Rendering Knowledge**: We acquired a solid understanding of rendering concepts, algorithms, and techniques, enabling us to generate visual output based on input data.
- **Problem-Solving Skills**: Throughout the project, we encountered various challenges and developed problem-solving skills in finding effective solutions.

Overall, this mini-project provided us with a valuable opportunity to apply theoretical knowledge to real-world software development. By creating a renderer in Java, we deepened our understanding of software engineering methodologies and gained practical skills that will serve as a foundation for future projects in the field.


## Introduction

The mini-project serves as a practical application of the principles and methods learned in the Introduction to Software Engineering course. Its purpose is to demonstrate the relevance of software engineering in real-world scenarios and to provide us, as students, with hands-on experience in the field.

The specific problem statement for this project is to develop a renderer that can generate high-quality images efficiently, while adhering to software engineering best practices. Rendering involves creating visual representations of objects or scenes based on input data. In this project, we focused on implementing rendering techniques, particularly ray tracing, to achieve realistic and visually appealing results.

The requirements for the renderer include producing images of good quality within optimal time frames, following best practices in software engineering, and avoiding any code smells. The aim is to develop a renderer that is efficient, maintainable, and scalable. This project enables us to apply software engineering methodologies to address these requirements, including requirements analysis, system design, implementation, testing, and maintenance.

By working on this mini-project, we not only gain a deeper understanding of software engineering principles but also acquire knowledge and practical skills in rendering and ray tracing. It allows us to explore the intersection between software engineering and graphics, bridging the gap between theory and real-world application. Additionally, the project reinforces the importance of optimal software design, even though quantitative criteria for optimality in software design may be subjective and based on judgment and experience.

Through this mini-project, we aim to develop a functional renderer that not only meets the specified requirements but also demonstrates our proficiency in software engineering methodologies. The knowledge and experience gained from this project will serve as a solid foundation for our future endeavors in the field of software engineering.

## Project Scope and Objectives

The scope of the project encompasses the development of a renderer that includes geometries, lighting, and primitives. The specific objectives of the project are as follows:

- Implement Geometries: Develop the necessary data structures and algorithms to represent and manipulate geometric models, such as polygons, spheres, or cubes.
- Lighting Implementation: Incorporate lighting effects into the renderer, including ambient, diffuse, and specular lighting, to enhance the visual quality of the rendered images.
- Primitives: Implement primitives such as points, rays, and vectors to support geometric calculations and transformations within the renderer.
- Unit Tests: Create comprehensive unit tests for each component of the renderer, including geometries, lighting, and primitives, to ensure their correctness and reliability.
- Testing: Conduct thorough testing of the renderer's functionalities, including unit tests, integration tests, and specific tests for features such as reflection, refraction, rendering improvements, shadows, and camera functionality.

The project will focus on the mentioned objectives and the specific components listed above. However, certain elements are excluded from the scope:

- User Interface (UI): The project will not include a graphical user interface or interactive controls for the renderer. The emphasis is on the core rendering functionalities and their implementation.
- Advanced Rendering Techniques: While the project involves rendering techniques such as reflection and refraction, more advanced techniques like global illumination or path tracing are beyond the scope of this mini-project.

By defining the scope and objectives, we can manage expectations and ensure that the project remains focused on its primary goals. The inclusion and exclusion of certain components help allocate resources and time effectively, enabling us to deliver a functional renderer that meets the specified requirements within the given timeframe.

## Methodology

For the mini-project, we adopted a software development methodology based on best practices from software engineering, with a focus on Object-Oriented Design (OOD) and iterative development.

**Object-Oriented Design (OOD):** We incorporated principles of OOD into our methodology, including encapsulation, inheritance, and polymorphism. OOD promotes modular, readable, and maintainable code by organizing software components into reusable and interconnected modules. By leveraging OOD, we aimed to create a flexible and extensible renderer with clear separation of concerns.

**Responsibility-Driven Design (RDD):** We followed the RDD approach, which focuses on identifying and assigning responsibilities to classes and objects based on their roles and behaviors within the system. This approach helped us create a well-structured design that encapsulates the necessary functionalities and promotes modularity.

**Design Patterns:** Throughout the project, we utilized several design patterns to solve common design problems and improve the overall structure of our code. These included the Iterator pattern, Template Method pattern, Composite pattern, Builder pattern, and Wrapper pattern. Each pattern provided specific benefits in terms of code organization, reusability, and flexibility.

**Ray Tracing and Rendering Techniques:** We focused on implementing ray tracing techniques within the renderer. Ray casting and ray tracing algorithms formed the foundation of our rendering process, enabling the generation of realistic images by simulating the behavior of light rays. We also incorporated the Phong Reflectance Model to enhance the visual quality of the rendered images, taking into account ambient, diffuse, and specular lighting components.

**Avoidance of Anti-patterns:** Throughout the development process, we aimed to identify and avoid common anti-patterns, such as needless complexity and unnecessary repetition. By applying code refactoring techniques, we continuously improved the clarity and maintainability of our codebase.

**Continual Improvement:** We explored various methods to improve ray tracing, such as implementing jittered sampling techniques to reduce artifacts and improve the overall quality of the rendered images. By incorporating these improvements, we strived to create a more visually appealing and realistic rendering output.

By following these methodologies and practices, we were able to effectively manage the project, balance requirements and constraints, and continuously improve the quality of our software solution. The iterative nature of our approach allowed us to incrementally develop and refine the renderer, ensuring that it met the specified requirements while adhering to best practices in software engineering.

## Requirements Analysis
Present the process of gathering, analyzing, and documenting the project requirements. Include use cases, functional and non-functional requirements, and any user stories or scenarios used.

## System Design
Discuss the high-level design of the software system, including architecture, data flow diagrams, class diagrams, or any other relevant design artifacts. Explain the rationale behind design decisions.

## Implementation
Describe the implementation phase, including the programming languages, tools, and frameworks used. Discuss any challenges faced during development and how they were addressed.

## Testing and Quality Assurance
Explain the testing approach used, such as unit testing, integration testing, or acceptance testing. Discuss the test cases executed and their results. Describe any quality assurance measures implemented.

## Deployment and Maintenance
Discuss the deployment strategy adopted, including the hosting environment, installation process, and user training, if applicable. Address any plans for future maintenance, updates, or enhancements.

## performance acceleration
## Conclusion
Summarize the main achievements of the mini-project and evaluate its success in meeting the objectives. Discuss any limitations or areas for future work.

## References
Cite all the sources, references, and frameworks utilized during the mini-project.

## Appendices
Include any supplementary materials, such as code snippets, screenshots, diagrams, or additional documentation.

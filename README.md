# DrawObjectEditor

## Implementation of Drawing Panel with Java

---

DrawObjectEditor is a Program where users can draw line, circle, triangle and quadrilateral. 

The created graphics can be selected, then user can move, copy, delete, randomly color the graphics object.

User can save and restore the state of the graphics objects by Save/Load/Export/Import functions (IO funnctions)

---

1. Object Creation 

Line : User click starting point, which will be displayed as a dot, followed by end point and the line will be drawn.

Circle : User's first click is the center of the circle. Center displayed as a dot. The user then clicks the radiuc to draw a circle.

Triangle : User clocks the top corner point, bottom left corner point annd bottom right corner.

Quadrilateral : User clicks the top left corner point, bottom left corner point, the bottom right corner and then the top right corner point.

2. Object Command (Button)

Select : User can select an object in the editor panel by clicking the area within the object. The Select button color will change to grey and not clickable before an object has been selected. When an object has been selected, the color of the selected object border will change to green. The user can then perform operations such as move or delete. The operation buttons [Move, Delete, Copy, Random Color] should become clickable.

Move : When an object has been selected, the user can press the Move button to move the object by press and release.

Delete : When an object has been selected, user can click the Delete button to delete the selected object in the editor panel.

Copy : When an object has been selected, the user can press the Copy button to copy the selected object. Your program should display the copied object near the original object (No strict requirement for the position of it, just don’t make it too far away). The copied object should just like other objects, and can be selected, moved, deleted, copied and colored.

Random Color function : When an object has been selected, user can click the Random Color button to fill the geometric object with random color. The colored object should just like other objects, and can be selected, moved, deleted, copied and re-colored.

3. Object IO function

Save : saves the object by using **serialization** to flatten the object and save it. 

Load : loads the objects by **deserialization**. 

Export : will export the object by interrogating the object and writing value of each instance variable to an ASCII file. 

`Geometric object type; Parameters list`

- Geometric object type stores the type of the Geometric object.
- Parameters list stores the necessary parameter for graphics initialization and the color information. The Geometric object color is stored as r (Color.Red), g (Color.Green), b (Color.Blue), which could be restored in standard RGB color space.


```
line;x1;y1;x2;y2;r;g;b
circle;x;y;radius;r;g;b
triangle;x1;y1;x2;y2;x3;y3;r;g;b
quadrilateral;no_of_sides;x1;y1;x2;y2 ... xn;yn;r;g;b
```

Import : will import and restore the graphics objects from the ASCII file.




import processing.sound.*;

Boolean whitepressed;
Boolean blackpressed;
SoundFile c3, cS3, d3, dS3, e3, f3, fS3, g3, gS3, a4, aS4, b4, c4, cS4, d4, dS4, e4, f4, fS4, g4, gS4, a5, aS5, b5, c5, cS5, d5;
int count;
int keyselect;
int keyselectB;
Boolean keypresser;

void setup() {
  size(800, 600);
  count = 0;
  textAlign(CENTER);
  textSize(42);
  whitepressed = false;
  blackpressed = false;
  keypresser = true;
  keyselect=-1;
  keyselectB=-1;
  c3 = new SoundFile(this, "c3.mp3");
  cS3 = new SoundFile(this, "cS3.mp3");
  d3 = new SoundFile(this, "d3.mp3");
  dS3 = new SoundFile(this, "dS3.mp3");
  e3 = new SoundFile(this, "e3.mp3");
  f3 = new SoundFile(this, "f3.mp3");
  fS3 = new SoundFile(this, "fS3.mp3");
  g3 = new SoundFile(this, "g3.mp3");
  gS3 = new SoundFile(this, "gS3.mp3");
  a4 = new SoundFile(this, "a4.mp3");
  aS4 = new SoundFile(this, "aS4.mp3");
  b4 = new SoundFile(this, "b4.mp3");
  c4 = new SoundFile(this, "c4.mp3");
  cS4 = new SoundFile(this, "cS4.mp3");
  d4 = new SoundFile(this, "d4.mp3");
  dS4 = new SoundFile(this, "dS4.mp3");
  e4 = new SoundFile(this, "e4.mp3");
  f4 = new SoundFile(this, "f4.mp3");
  fS4 = new SoundFile(this, "fS4.mp3");
  g4 = new SoundFile(this, "g4.mp3");
  gS4 = new SoundFile(this, "gS4.mp3");
  a5 = new SoundFile(this, "a5.mp3");
  aS5 = new SoundFile(this, "aS5.mp3");
  b5 = new SoundFile(this, "b5.mp3");
  c5 = new SoundFile(this, "c5.mp3");
  cS5 = new SoundFile(this, "cS5.mp3");
  d5 = new SoundFile(this, "d5.mp3");
}

void draw() {
  fill(100, 75, 20);
  rect(0, 0, width, 300);
  fill(255);
  text("Mitch's Virtual", width/2, 60);
  text("Piano", width/2, 100);
  for (int i=0; i<20; i++) {
    fill(255);
    rect((width/16)*i, 300, (width/16), 300);
  }
  fill(0);
  for (int i=0; i<4; i++) {
    if (i%2==0) {
      rect(35+i*100, 300, 30, 150);
    }
    rect(85+i*100, 300, 30, 150);
  }
  for (int i=0; i<4; i++) {
    if (i==1) {
      rect(585, 300, 30, 150);
    }
    rect(435+i*100, 300, 30, 150);
  }

  switch(keyselect)
  {
  case 0:
    fill(0);
    rect(0, 300, 50, 300);
    c3.play();
    break;
  case 1:
    fill(0);
    rect(50, 300, 50, 300);
    d3.play();
    break;
  case 2:
    fill(0);
    rect(100, 300, 50, 300);
    e3.play();
    break;
  case 3:
    fill(0);
    rect(150, 300, 50, 300);
    f3.play();
    break;
  case 4:
    fill(0);
    rect(200, 300, 50, 300);
    g3.play();
    break;
  case 5:
    fill(0);
    rect(250, 300, 50, 300);
    a4.play();
    break;
  case 6:
    fill(0);
    rect(300, 300, 50, 300);
    b4.play();
    break;
  case 7:
    fill(0);
    rect(350, 300, 50, 300);
    c4.play();
    break;
  case 8:
    fill(0);
    rect(400, 300, 50, 300);
    d4.play();
    break;
  case 9:
    fill(0);
    rect(450, 300, 50, 300);
    e4.play();
    break;
  case 10:
    fill(0);
    rect(500, 300, 50, 300);
    f4.play();
    break;
  case 11:
    fill(0);
    rect(550, 300, 50, 300);
    g4.play();
    break;
  case 12:
    fill(0);
    rect(600, 300, 50, 300);
    a5.play();
    break;
  case 13:
    fill(0);
    rect(650, 300, 50, 300);
    b5.play();
    break;
  case 14:
    fill(0);
    rect(700, 300, 50, 300);
    c5.play();
    break;
  case 15:
    fill(0);
    rect(750, 300, 50, 300);
    d5.play();
    break;
  }
  switch(keyselectB)
  {

  case 0:
    fill(255);
    rect(85, 300, 30, 150);
    dS3.play();
    break;
  case 1:
    fill(255);
    rect(185, 300, 30, 150);
    fS3.play();
    break;

  case 2:
    fill(255);
    rect(285, 300, 30, 150);
    aS4.play();
    break;
  case 3:
    fill(255);
    rect(385, 300, 30, 150);
    cS4.play();
    break;
  case 5:
    fill(255);
    rect(585, 300, 30, 150);
    gS4.play();
    break;
  case 6:
    fill(255);
    rect(35, 300, 30, 150);
    cS3.play();
    break;
  case 7:
    fill(255);
    rect(235, 300, 30, 150);
    gS3.play();
    break;
  case 8:
    fill(255);
    rect(435, 300, 30, 150);
    dS4.play();
    break;
  case 9:
    fill(255);
    rect(535, 300, 30, 150);
    fS4.play();
    break;
  case 10:
    fill(255);
    rect(635, 300, 30, 150);
    aS5.play();
    break;
  case 11:
    fill(255);
    rect(735, 300, 30, 150);
    cS5.play();
    break;
  }
}

void mousePressed() {
  if (mouseY<450 && mouseY>300 && whitepressed == false) {
    for (int i=0; i<6; i++) {
      if (mouseX>(85+i*100) && mouseX<(115+100*i) && i!=4) {
        keyselectB=i;
      }
    }
    if (mouseX>35 && mouseX <65) {
      keyselectB=6;
    } else if (mouseX>235 && mouseX < 265) {
      keyselectB = 7;
    }
    for (int i=0; i<8; i++) {
      println(mouseX>(35+(i*100)), mouseX<(65+(100*i)), i);
      if (mouseX>(35+(i*100)) && mouseX<(65+(100*i)) && (i==1 || i>2)) {
        keyselectB=4+i;
      }
    }
  }
  if (mouseY>450) {
    for (int i=0; i<16; i++) {
      if ((mouseX - ((width/16)*i) < 50 && mouseX-((width/16)*i) > 0 && blackpressed ==false)) {
        whitepressed=true; 
        keyselect=i;
      }
    }
  }
}

void mouseReleased() {
  whitepressed=false; 
  blackpressed=false; 
  keyselect=-1; 
  keyselectB=-1;
}

void keyPressed() {
  if (key=='z') {
    keyselect=0;
  } else if (key=='x') {
    keyselect=1;
  } else if (key=='c') {
    keyselect=2;
  } else if (key=='v') {
    keyselect=3;
  } else if (key=='b') {
    keyselect=4;
  } else if (key=='n') {
    keyselect=5;
  } else if (key=='m') {
    keyselect=6;
  } else if (key==',' || key=='q') {
    keyselect=7;
  } else if (key=='.' || key=='w') {
    keyselect=8;
  } else if (key=='/' || key=='e') {
    keyselect=9;
  } else if (key=='r') {
    keyselect=10;
  } else if (key=='t') {
    keyselect=11;
  } else if (key=='y') {
    keyselect=12;
  } else if (key=='u') {
    keyselect=13;
  } else if (key=='i') {
    keyselect=14;
  } else if (key=='o') {
    keyselect=15;
  } else if (key=='s') {
    keyselectB=6;
  } else if (key=='d') {
    keyselectB=0;
  } else if (key=='g') {
    keyselectB=1;
  } else if (key=='h') {
    keyselectB=7;
  } else if (key=='j') {
    keyselectB=2;
  } else if (key=='l' || key=='2') {
    keyselectB=3;
  } else if (key==';' || key=='3') {
    keyselectB=8;
  } else if (key=='5') {
    keyselectB=9;
  } else if (key=='6') {
    keyselectB=5;
  } else if (key=='7') {
    keyselectB=10;
  } else if (key=='9') {
    keyselectB=11;
  }
}

void keyReleased() {
  whitepressed=false; 
  blackpressed=false; 
  keyselect=-1; 
  keyselectB=-1;
}

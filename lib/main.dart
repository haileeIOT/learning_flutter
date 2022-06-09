import 'package:flutter/material.dart';
import 'package:test_getx/views/homepage.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {

    return MaterialApp(
debugShowCheckedModeBanner: false,
      title: 'test getx',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity
      ),
       home: HomePage(),
    );
  }
}

import { Component, OnInit } from '@angular/core';
import { Icons } from 'src/app/enums/icons.enum';
import { TableHeader } from '../../gen-table/table-header';
import { TableOperation } from '../../gen-table/table-operation';
import { Book } from '../../model/book';
import { BookService } from '../../services/book.service';

@Component({
  selector: 'app-allbooks',
  templateUrl: './allbooks.component.html',
  styleUrls: ['./allbooks.component.sass']
})
export class AllbooksComponent implements OnInit {
  bookList: Book[] = [];

  tableHeader: TableHeader[] = [
    {
      headerName: 'Title',
      fieldName: ['title']
    },
    {
      headerName: 'Page Number',
      fieldName: ['pageNum']
    },
    {
      headerName: 'View Number',
      fieldName: ['viewNumber']
    },
    {
      headerName: 'Year of Publishing',
      fieldName: ['yearOfPublishing']
    }
  ];

  operations: TableOperation<Book>[] = [
    {
      operation: (element) => this.read(element),
      icon: Icons.read
    }
  ];

  constructor(private service: BookService) { }

  ngOnInit(): void {
    this.service.getAllBooks().subscribe(
      res => {
        this.bookList = res.body as Book[];
      }

    );
  }

  read(book) {

  }

}

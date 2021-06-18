import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Icons } from 'src/app/enums/icons.enum';
import { AddGradeBookComponent } from '../add-grade-book/add-grade-book.component';
import { TableHeader } from '../gen-table/table-header';
import { TableOperation } from '../gen-table/table-operation';
import { Book } from '../model/book';
import { Favorite } from '../model/favorite';
import { AuthService } from '../services/auth.service';
import { BookService } from '../services/book.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-userreadbook',
  templateUrl: './userreadbook.component.html',
  styleUrls: ['./userreadbook.component.sass']
})
export class UserreadbookComponent implements OnInit {

  bookList: Book[] = [];
  favoriteBook: Favorite = {id: 1, userId: 1};

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
      headerName: 'Author Name',
      fieldName: ['authorName']
    },
    {
      headerName: 'Year of Publishing',
      fieldName: ['yearOfPublishing']
    },
    {
      headerName: 'Genres',
      fieldName: ['genres']
    },
    {
      headerName: 'User Score',
      fieldName: ['userScore']
    },
    {
      headerName: 'User favorite book',
      fieldName: ['favored']
    }
  ];

  operations: TableOperation<Book>[] = [
    {
      operation: (element) => this.grade(element),
      icon: Icons.grade
    },
    {
      operation: (element) => this.favorit(element),
      icon: Icons.favorit
    }
  ];

  constructor(private service: UserService, private modalService: NgbModal, private authService: AuthService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData(){
    this.service.getUserReadBooks(this.authService.getUserId()).subscribe(
      res => {
        this.bookList = res.body as Book[];
      }

    );
  }

  grade(book){
    console.log("grade");
    
    const modalRef = this.modalService.open(AddGradeBookComponent,
      { ariaLabelledBy: 'score-book', size: 'lg', scrollable: true });
   modalRef.componentInstance.book = book;
   modalRef.componentInstance.refresh = () => { this.loadData(); };
  }

  favorit(book){
    console.log("favorit");
    this.favoriteBook.id = book.id;
    this.favoriteBook.userId = this.authService.getUserId();
    this.service.setFavoriteBook(this.favoriteBook).subscribe(
      res => {

        this.loadData();
      },
      error => {
        
      }

    );
  }

}

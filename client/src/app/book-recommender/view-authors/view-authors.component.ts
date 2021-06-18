import { Component, OnInit } from '@angular/core';
import { Icons } from 'src/app/enums/icons.enum';
import { TableHeader } from '../gen-table/table-header';
import { TableOperation } from '../gen-table/table-operation';
import { Author } from '../model/author';
import { Book } from '../model/book';
import { Favorite } from '../model/favorite';
import { AuthService } from '../services/auth.service';
import { AuthorService } from '../services/author.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-view-authors',
  templateUrl: './view-authors.component.html',
  styleUrls: ['./view-authors.component.sass']
})
export class ViewAuthorsComponent implements OnInit {

  authorList: Author[] = [];
  favoriteAuthor: Favorite = {id: 1, userId: 1};

  tableHeader: TableHeader[] = [
    {
      headerName: 'Name',
      fieldName: ['name']
    },
    {
      headerName: 'User favorite author',
      fieldName: ['favored']
    }
  ];

  operations: TableOperation<Author>[] = [
    {
      operation: (element) => this.favorit(element),
      icon: Icons.favorit
    }
  ];

  constructor(private service: AuthorService, private authService: AuthService, private userService: UserService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData(){
    this.service.getUserViewAuthors(this.authService.getUserId()).subscribe(
      res => {
        this.authorList = res.body as Author[];
      }

    );
  }

  favorit(author){
    console.log("favorit");
    this.favoriteAuthor.id = author.id;
    this.favoriteAuthor.userId = this.authService.getUserId();
    this.userService.setFavoriteAuthor(this.favoriteAuthor).subscribe(
      res => {

        this.loadData();
      },
      error => {
        
      }

    );
  }

}

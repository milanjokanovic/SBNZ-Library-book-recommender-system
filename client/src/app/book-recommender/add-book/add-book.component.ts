import { Component, Input, OnInit } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Author } from '../model/author';
import { Book } from '../model/book';
import { Genre } from '../model/genre';
import { AuthorService } from '../services/author.service';
import { BookService } from '../services/book.service';
import { GenreService } from '../services/genre.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.sass']
})
export class AddBookComponent implements OnInit {

  @Input() refresh;
  badRequest = false;
  unauthorized = false;
  repeatedBook = false;
  book: Book={title: '', pageNum: 0, authorName: '', genres: [], yearOfPublishing: 2021};
  genres = [];
  genre = '';
  authors: Author[] = [];
  genreList: Genre[] = [];
  constructor(public activeModal: NgbActiveModal,
              private service: BookService, private authorService: AuthorService, private genreService: GenreService) {
                this.loadData();
  }

  ngOnInit(): void {
    this.loadData();
    //this.genre = this.genreList[0].name;
  }

  changeGenre(){
    console.log("Usao");
  }

  loadData(){
    this.authorService.getAllAuthors().subscribe(
      res => {
        this.authors = res.body as Author[];
        this.book.authorName = this.authors[0].name;
      }

    );

    this.genreService.getAllGenres().subscribe(
      res => {
        this.genreList = res.body as Genre[];
        this.genre = this.genreList[0].name;
      }

    );
  }

  addGenre() {
    console.log(this.genre);
    if (this.genres.indexOf(this.genre) === -1) {
      this.genres.push(this.genre);
      //this.genre = '';
      this.genre = this.genreList[0].name;
      this.repeatedBook = false;
    } else {
      this.repeatedBook = true;
    }
  }

  remove(index) {
    this.genres.splice(index, 1);
    this.repeatedBook = false;
  }

  add() {
    this.repeatedBook = false;
    this.book.genres = this.genres;
    console.log(this.book.genres);
    console.log(this.book.title);
    console.log(this.book.authorName);
    this.service.create(this.book).subscribe(
      res => {
        this.refresh();
        this.activeModal.close();
        this.badRequest = false;
        this.unauthorized = false;
      },
      error => {
        if (error.status === 400) {
          this.badRequest = true;
        }
      }

    );
  }

}

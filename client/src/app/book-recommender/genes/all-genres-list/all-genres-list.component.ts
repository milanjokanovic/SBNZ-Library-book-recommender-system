import { Component, OnInit } from '@angular/core';
import { faHome } from '@fortawesome/free-solid-svg-icons';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TableHeader } from '../../gen-table/table-header';
import { TableOperation } from '../../gen-table/table-operation';
import { Genre } from '../../model/genre';
import { GenreService } from '../../services/genre.service';
import { AddGenreComponent } from '../add-genre/add-genre.component';

@Component({
  selector: 'app-all-genres-list',
  templateUrl: './all-genres-list.component.html',
  styleUrls: ['./all-genres-list.component.sass']
})
export class AllGenresListComponent implements OnInit {

  genreList: Genre[] = [];
  faHomeIcon = faHome;

  tableHeader: TableHeader[] = [
    {
      headerName: 'Name',
      fieldName: ['name']
    }
  ];

  operations: TableOperation<Genre>[] = [
    
  ];

  constructor(private service: GenreService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData(){
    this.service.getAllGenres().subscribe(
      res => {
        this.genreList = res.body as Genre[];
      }

    );
  }

  add(){
    const modalRef = this.modalService.open(AddGenreComponent, { ariaLabelledBy: 'add-genre', size: 'lg', scrollable: true });
    modalRef.componentInstance.refresh = () => { this.loadData(); };
  }

}

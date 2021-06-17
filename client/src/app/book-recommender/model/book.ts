export interface Book {
    id?: number;
    pageNum: number;
    viewNumber: number;
    title: string;
    series: string;
    seriesNumber: number;
    targetAudience: string;
    basedOnRealEvent: boolean;
    nobelPrize: boolean;
    yearOfPublishing: number;
    authorName: string;
    genres: string[];
    avgScore: number;
}

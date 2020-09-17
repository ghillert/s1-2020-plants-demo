import { Observable, of } from 'rxjs';

/**
 * Provides pagination support.
 *
 * @author Gunnar Hillert
 */
export class Page<T> {
  totalPages = 0;
  totalElements = 0;

  pageNumber = 0;
  pageSize = 10;
  items: T[] = [];
  filter = {};
  sort = {};

  public static fromJSON<T>(input: any): Page<T> {
    const page = new Page<T>();
    if (input) {
      page.pageNumber = input.number;
      page.pageSize = input.size;
      page.totalElements = input.totalElements;
      page.totalPages = input.totalPages;
    } else {
      console.error('input', input);
      throw new Error('Was expecting a page property.');
    }
    return page;
  }

  public getItemsAsObservable(): Observable<T[]> {
    return of(this.items);
  }

  public update(page: Page<T> ) {
    this.items.length = 0;
    this.items.push(...page.items);
    this.pageNumber = page.pageNumber;
    this.pageSize = page.pageSize;
    this.totalElements = page.totalElements;
    this.totalPages = page.totalPages;
  }

}

import { Serializable } from './serializable.model';
import { Page } from './page';

/**
 * Represents a Plant.
 *
 * @author Gunnar Hillert
 */
export class Plant implements Serializable<Plant> {

  public id?: number;
  public genus?: string;
  public species?: string;
  public commonName?: string;
  public distance?: number;
  public plantSignMissing?: boolean;
  public plantsNearby?: Plant[]
  public imageIds?: number[];

  public static fromJSON(input: any) {
    return new Plant().deserialize(input);
  }

  public static pageFromJSON(input: any): Page<Plant> {
    const page = Page.fromJSON<Plant>(input);
    if (input && input.content) {
      page.items = input.content.map(Plant.fromJSON);
    }
    return page;
  }

  public deserialize(input: any) {
    this.id = input.id;
    this.genus = input.genus;
    this.species = input.species;
    this.commonName = input.commonName;
    this.distance = input.distance;
    this.plantSignMissing = input.plantSignMissing;
    this.plantsNearby = input.plantsNearby;
    this.imageIds = input.imageIds;
    return this;
  }

}
